package com.github.simplesteph.avro.specific;

import com.example.Customer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SpecificRecordExamplesTwo {

    public static void main(String[] args) {
        // Step 1: create specific record
        Customer.Builder customerBuilder = Customer.newBuilder();

        customerBuilder.setFirstNme("Alain");
        customerBuilder.setLastName("Tientcheu");
        customerBuilder.setAge(25);
        customerBuilder.setHeight(185f);
        customerBuilder.setWeight(100f);

        Customer customer = customerBuilder.build();

        System.out.println("Customer: " + customer);

        // Step 2: Write to file
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
            dataFileWriter.append(customer);
            System.out.println("successfully wrote customer-specific.avro");
        } catch (IOException e){
            e.printStackTrace();
        }

        // Step 3: Read from file
        final File file = new File("customer-specific.avro");
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        final DataFileReader<Customer> dataFileReader;
        try {
            System.out.println("Reading our specific record");
            dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                Customer readCustomer = dataFileReader.next();
                System.out.println(readCustomer.toString());
                System.out.println("First name: " + readCustomer.getFirstNme());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
