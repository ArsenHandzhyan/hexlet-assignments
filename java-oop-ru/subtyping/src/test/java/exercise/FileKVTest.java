package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// BEGIN

// END


class FileKVTest {

    private static final Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    @Test
    public void testSetAndGetValue() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");
        KeyValueStorage fileKV = new FileKV(filepath.toString(), initialData);

        fileKV.set("key3", "value3");
        fileKV.set("key4", "value4");

        assertEquals("value1", fileKV.get("key1", ""));
        assertEquals("value2", fileKV.get("key2", ""));
        assertEquals("value3", fileKV.get("key3", ""));
        assertEquals("value4", fileKV.get("key4", ""));
    }

    @Test
    public void testUnsetValue() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");
        KeyValueStorage fileKV = new FileKV(filepath.toString(), initialData);

        fileKV.unset("key1");

        assertEquals("", fileKV.get("key1", ""));
        assertEquals("value2", fileKV.get("key2", ""));
    }

    @Test
    public void testGetDefaultValue() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");
        KeyValueStorage fileKV = new FileKV(filepath.toString(), initialData);

        assertEquals("default", fileKV.get("key3", "default"));
        assertEquals("", fileKV.get("key4", ""));
    }

    @Test
    public void testToMap() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");
        KeyValueStorage fileKV = new FileKV(filepath.toString(), initialData);

        Map<String, String> actualData = fileKV.toMap();

        assertEquals(2, actualData.size());
        assertEquals("value1", actualData.get("key1"));
        assertEquals("value2", actualData.get("key2"));
    }
}
