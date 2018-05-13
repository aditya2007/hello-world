package com.helloworld.rest.dev.serializer;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.helloworld.rest.dev.dto.Customer;
import com.helloworld.rest.dev.dto.Item;

import java.io.IOException;
import java.lang.reflect.Field;

public class SerializerApp {
	private ObjectMapper MAPPER = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException, IOException {
		/*SerializerApp app = new SerializerApp();
		Item item = new Item(1, "testItem",
				Customer.builder().customerId(1).firstName("Yoga").lastName("Gowda").build());
		//Use below approach or use @JsonSerialize on Item class
		SimpleModule module = new SimpleModule();
		//module.addSerializer(Item.class, app.new ItemSerializer());
		module.addDeserializer(Item.class, app.new ItemDeSerializer());
		app.MAPPER.registerModule(module);
		String serialized = app.MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(item);
		System.out.println("Serialized Data >>>>>>> " + serialized);
		Item retItem = app.MAPPER.readValue(serialized, Item.class);
		System.out.println("Deserialized Data <<<<<<<< " + retItem);*/
	}

	public class ItemSerializer extends StdSerializer<Item> {

		public  ItemSerializer() {
			this(Item.class);
		}

		public ItemSerializer(Class<Item> t) {
			super(t);
		}

//		protected ItemSerializer(JavaType type) {
//			super(type);
//		}

//		public ItemSerializer(Class<?> t, boolean dummy) {
//			super(t, dummy);
//		}

		@Override
		public void serialize(Item item, JsonGenerator jsonGenerator,
							  SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("id", item.getId());
			jsonGenerator.writeStringField("itemName", item.getItemName());
			jsonGenerator.writeNumberField("ownerId", item.getOwner().getCustomerId());
			jsonGenerator.writeStringField("ownerFName", item.getOwner().getFirstName());
			jsonGenerator.writeStringField("ownerLName", item.getOwner().getLastName());
			jsonGenerator.writeEndObject();
		}
	}

	public class ItemDeSerializer extends StdDeserializer<Item> {

		public ItemDeSerializer() {
			super(Item.class);
		}

		@Override
		public Item deserialize(
				JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {

			JsonToken currToken = null;
			Class itemClass = null;
			Item item = null;
			try {
				itemClass = Class.forName("com.helloworld.rest.dev.dto.Item");
				item = (Item)itemClass.newInstance();
				while ((currToken = jsonParser.nextValue()) != null) {
					Field[] fields = itemClass.getDeclaredFields();
					System.out.println("Current Token : " + currToken + ", CurrentName :: " + jsonParser.getCurrentName());
					for (Field field : fields) {
						field.setAccessible(true);
						//Object val = currToken.equals(JsonToken.VALUE_NUMBER_INT) ? jsonParser.getValueAsInt() : jsonParser.getValueAsString();
						//System.out.println("Current Token Type :: " + currToken + ", and its value :: " + jsonParser.getValueAsString());
						if (currToken.equals(JsonToken.VALUE_NUMBER_INT) && field.getName().equals(jsonParser.getCurrentName())) {
							System.out.println(field.getName() + " :: Int fields >> " + jsonParser.getCurrentName() + " :: " + jsonParser.getValueAsInt());
							//if (field.getName())
							field.setInt(item, jsonParser.getValueAsInt());
						} else if (currToken.equals(JsonToken.VALUE_STRING) && field.getName().equals(jsonParser.getCurrentName())) {
							System.out.println(field.getName() + " :: String fields >> " + jsonParser.getCurrentName() + " :: " + jsonParser.getValueAsString());
							field.set(item, jsonParser.getValueAsString());
						} else if(currToken.equals(JsonToken.START_OBJECT) && field.getName().equals(jsonParser.getCurrentName())) {
							System.out.println("Embeded Object ::: " + jsonParser.getCurrentName());
						}
					}
				} // end of while loop
			} catch(ClassNotFoundException cne) {
				cne.printStackTrace();
			} catch (IllegalAccessException iae) {
				iae.printStackTrace();
			} catch (InstantiationException ie) {
				ie.printStackTrace();
			}
			return item;
		}
	}

}
