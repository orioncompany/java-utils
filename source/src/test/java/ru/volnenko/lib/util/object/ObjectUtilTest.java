package ru.volnenko.lib.util.object;

import ru.volnenko.lib.util.dto.Foo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by orion on 16.04.15.
 */
public class ObjectUtilTest {

    @Test
    public void serializeStringTest() throws IOException, ClassNotFoundException {
        final Foo fooSource = new Foo("1", "2");
        final String fooSourceString = ObjectUtil.objectToString(fooSource);
        Assert.assertNotNull(fooSourceString);

        final Foo fooDestination = (Foo) ObjectUtil.objectFromString(fooSourceString);
        final String fooDestinationString = ObjectUtil.objectToString(fooDestination);
        Assert.assertNotNull(fooDestination);

        Assert.assertEquals(fooSourceString, fooDestinationString);
        Assert.assertEquals(fooSource.a, fooDestination.a);
        Assert.assertEquals(fooSource.b, fooDestination.b);
    }

    @Test
    public void serializeXMLTest() throws IOException {
        final Foo fooSource = new Foo("1", "2");
        final String fooSourceXML = ObjectUtil.objectToXML(fooSource);
        Assert.assertNotNull(fooSourceXML);

        final Foo fooDestination = (Foo) ObjectUtil.objectFromXML(fooSourceXML);
        final String fooDestinationXML = ObjectUtil.objectToXML(fooDestination);
        Assert.assertNotNull(fooDestination);

        Assert.assertEquals(fooSourceXML, fooDestinationXML);
        Assert.assertEquals(fooSource.a, fooDestination.a);
        Assert.assertEquals(fooSource.b, fooDestination.b);
    }

}
