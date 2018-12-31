package kryo;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import sun.plugin2.message.Conversation;
import sun.plugin2.message.Message;

public class KryoTest {

    private Kryo kryo;
    private Input input;
    private Output output;

    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;

    @Before
    public void init() throws FileNotFoundException {
        kryo = new Kryo();

        fileOutputStream = new FileOutputStream("file.dat");
        fileInputStream = new FileInputStream("file.dat");

        output = new Output(fileOutputStream);
        input = new Input(fileInputStream);
    }

    @Test
    public void givenObject_whenSerializing_thenReadCorrectly() {
        Object someObject = "Some string";

        kryo.writeClassAndObject(output, someObject);
        output.close();

        Object theObject = kryo.readClassAndObject(input);
        input.close();

        assertEquals(theObject, "Some string");
    }

    @Test
    public void givenObjects_whenSerializing_thenReadCorrectly() {
        String someString = "Multiple Objects";
        Date someDate = new Date(915170400000L);

        kryo.writeObject(output, someString);
        kryo.writeObject(output, someDate);
        output.close();

        String readString = kryo.readObject(input, String.class);
        Date readDate = kryo.readObject(input, Date.class);
        input.close();

        assertEquals(readString, "Multiple Objects");
        assertEquals(readDate.getTime(), 915170400000L);
    }

    @Test
    public void givenPerson_whenSerializing_thenReadCorrectly() {
        Person person = new Person();

        kryo.writeObject(output, person);
        output.close();

        Person readPerson = kryo.readObject(input, Person.class);
        input.close();

        assertEquals(readPerson.getName(), "John Doe");
    }

    @Test
    public void givenPerson_whenUsingCustomSerializer_thenReadCorrectly() {
        Person person = new Person();
        person.setAge(0);

        kryo.register(Person.class, new PersonSerializer());
        kryo.writeObject(output, person);
        output.close();

        Person copyPerson = kryo.copy(person);
        Person readPerson = kryo.readObject(input, Person.class);
        input.close();

        assertEquals(copyPerson.getName(), "John Doe");
        assertEquals(copyPerson.getAge(), 18);
    }

    @Test
    public void givenPerson_whenWithNoDefaultConstructor_thenReadCorrectly() {
        NoDefaultConstructorChild child = new NoDefaultConstructorChild(1);

        kryo.writeObject(output, child);
        output.close();

        NoDefaultConstructor readChild = kryo.readObject(input, NoDefaultConstructorChild.class);
        input.close();

        assertEquals(child.getNumber(), 1);
    }

    @Test
    public void missingNoArgConstructorTest() {
        List<String> receivedMids = Lists.newArrayList("adfd");

        //Set<String> failedMids = SetsTest.newHashSet(receivedMids);
        Set<String> failedMids = Sets.newHashSet(new ArrayList<String>());

        Kryo kryo = new Kryo();
        //kryo.register(Set.class);

        kryo.writeObject(output, failedMids);
        output.close();

        Set<String> failedMids2 = kryo.readObject(input, Set.class);
    }
}
