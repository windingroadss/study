package kryo;

import java.io.IOException;

import sun.plugin2.message.Conversation;
import sun.plugin2.message.Message;
import sun.plugin2.message.Serializer;

public class FixMessage extends Message {

  public FixMessage(int i, Conversation conversation) {
    super(i, conversation);
  }

  public void writeFields(Serializer serializer) throws IOException {

  }

  public void readFields(Serializer serializer) throws IOException {

  }
}
