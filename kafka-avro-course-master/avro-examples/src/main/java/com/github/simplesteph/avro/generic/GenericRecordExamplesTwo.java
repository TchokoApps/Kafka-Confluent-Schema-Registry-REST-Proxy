package com.github.simplesteph.avro.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.*;
import org.apache.avro.io.DatumReader;

import java.io.File;
import java.io.IOException;

public class GenericRecordExamplesTwo {

    public static void main(String[] args) {

        // Step 1: Define the schema
        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse("{\n" +
                "     \"type\": \"record\",\n" +
                "     \"namespace\": \"com.example\",\n" +
                "     \"name\": \"Customer\",\n" +
                "     \"fields\": [\n" +
                "       { \"name\": \"firstName\", \"type\": \"string\", \"doc\": \"First Name of Customer\" },\n" +
                "       { \"name\": \"lastName\", \"type\": \"string\", \"doc\": \"Last Name of Customer\" },\n" +
                "       { \"name\": \"age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\" },\n" +
                "       { \"name\": \"height\", \"type\": \"float\", \"doc\": \"Height at the time of registration in cm\" },\n" +
                "       { \"name\": \"weight\", \"type\": \"float\", \"doc\": \"Weight at the time of registration in kg\" },\n" +
                "       { \"name\": \"automatedEmail\", \"type\": \"boolean\", \"default\": true, \"doc\": \"Field indicating if the user is enrolled in marketing emails\" }\n" +
                "     ]\n" +
                "}");

        // Step 2: create generate Record
        GenericRecordBuilder customerGenericRecordBuilder = new GenericRecordBuilder(schema);

        customerGenericRecordBuilder.set("firstName", "Nadine");
        customerGenericRecordBuilder.set("lastName", "Tchokonte");
        customerGenericRecordBuilder.set("age", 20);
        customerGenericRecordBuilder.set("height", 162f);
        customerGenericRecordBuilder.set("weight", 65f);

        GenericData.Record customerRecord = customerGenericRecordBuilder.build();

        System.out.println("CustomerRecord: " + customerRecord);

        // Step 3: Write the generic record to a file
        GenericDatumWriter<GenericRecord> genericDatumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(genericDatumWriter)) {
            dataFileWriter.create(schema, new File("customer-generic.avro"));
            dataFileWriter.append(customerRecord);
            System.out.println("Written customer-generic.avro");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Step 4: Read the generic record from the file
        final File file = new File("customer-generic.avro");

        final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        GenericRecord customerRead;
        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader)) {
            while (dataFileReader.hasNext()) {

                customerRead = dataFileReader.next();
                System.out.println("Successfully read avro file");
                System.out.println(customerRead.toString());

                // get the data from the generic record
                System.out.println("First name: " + customerRead.get("firstName"));

                // read a non existent field
                System.out.println("Non existent field: " + customerRead.get("not_here"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
