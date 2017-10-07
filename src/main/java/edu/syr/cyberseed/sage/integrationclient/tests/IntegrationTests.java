package edu.syr.cyberseed.sage.integrationclient.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.syr.cyberseed.sage.integrationclient.entities.SuperSetOfAllMedicalRecordTypes;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

@Component
public class IntegrationTests {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    // Read API user details from property file
    private String initialSysadminUsername;
    private String initialSysadminPassword;
    private String sysadminPassword;
    private String doctorPassword;
    private String nursePassword;
    private String patientPassword;
    private String insadminPassword;
    private String medadminPassword;

    // create logger
    private static final Logger log = LoggerFactory.getLogger(IntegrationTests.class);

    private String smirkHost = "";
    private String url = "";
    private String smirkService = "";
    private String requestUsername = "";
    private String requestPassword = "";
    private String returnedDataFromAPI = "";
    private String[] recordSummaryList = null;
    private String recordId = "";

    @PostConstruct
    public void init() {

        // Read API details from property file
        smirkHost = env.getProperty("smirk.api.protocol")
                + env.getProperty("smirk.api.host")
                + ":" + env.getProperty("smirk.api.port");

        // Read API user details from property file
        initialSysadminUsername = env.getProperty("smirk.api.initial.sysadmin.username");
        initialSysadminPassword = env.getProperty("smirk.api.initial.sysadmin.password");
        sysadminPassword = env.getProperty("smirk.api.integration.test.sysadmin.password");
        doctorPassword = env.getProperty("smirk.api.integration.test.doctor.password");
        nursePassword = env.getProperty("smirk.api.integration.test.nurse.password");
        patientPassword = env.getProperty("smirk.api.integration.test.patient.password");
        insadminPassword = env.getProperty("smirk.api.integration.test.insadmin.password");
        medadminPassword = env.getProperty("smirk.api.integration.test.medadmin.password");
        ArrayList<String> recordSummaryList = new ArrayList<String>();

        System.out.println("");
        System.out.println("Starting Integration Tests");
        System.out.println("");

        integrationTest1();
        integrationTest2();
        integrationTest3();
        integrationTest4();
        integrationTest5();
        integrationTest6();
        //integrationTest7();
        //integrationTest8();
        //integrationTest9();
        //integrationTest10();
        //integrationTest11();
        //integrationTest12();
        //integrationTest13();
        //integrationTest14();
        integrationTest15();
        //integrationTest16();
        //integrationTest17();
        //integrationTest18();
        //integrationTest19();
        //integrationTest20();
        //integrationTest21();
        integrationTest22();
        integrationTest23();
        //integrationTest24();
        //integrationTest25();
        //integrationTest26();
        //integrationTest27();
        //integrationTest28();

        System.out.println("");
        System.out.println("Integration Tests Complete");
        System.out.println("");

        return;

    }

    private void integrationTest1 () {

        smirkService = "/createSysAdmin";
        url = smirkHost + smirkService;
        requestUsername = initialSysadminUsername;
        requestPassword = initialSysadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "DMurphy");
        objectNode.put("password", sysadminPassword);
        objectNode.put("fname", "Dade");
        objectNode.put("lname", "Murphy");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest2 () {

        smirkService = "/createDoctor";
        url = smirkHost + smirkService;
        requestUsername = "DMurphy";
        requestPassword = sysadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "KLibby");
        objectNode.put("password", doctorPassword);
        objectNode.put("fname", "Kate");
        objectNode.put("lname", "Libby");
        objectNode.put("practiceName", "The Gibson Associates");
        objectNode.put("practiceAddress", "1995 Aburn Road, Chicago, IL 60007");
        objectNode.put("recoveryPhrase", "You are not in my class");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest3 () {

        smirkService = "/createPatient";
        url = smirkHost + smirkService;
        requestUsername = "KLibby";
        requestPassword = doctorPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "MBishop");
        objectNode.put("password", patientPassword);
        objectNode.put("fname", "Martin");
        objectNode.put("lname", "Bishop");
        objectNode.put("dob", "1936-08-18T00:00:00.000Z");
        objectNode.put("ssn", "312124253");
        objectNode.put("address", "145 Redford Drive, Baltimore, MD 21234");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest4 () {

        smirkService = "/addDoctorExamRecord";
        url = smirkHost + smirkService;
        requestUsername = "KLibby";
        requestPassword = doctorPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("id", "9123245");
        objectNode.put("examDate", "2017-09-03T00:00:00.000Z");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("doctorUsername", "KLibby");
        objectNode.put("notes", "Looks great for his age");
        //edit permission users
        ArrayNode editArrayNode = objectNode.putArray("edit");
        editArrayNode.add("MBishop");
        //view permission users
        ArrayNode viewArrayNode = objectNode.putArray("view");
        viewArrayNode.add("MBishop");
        viewArrayNode.add("KLibby");

        //ArrayList<String> editUserList = new ArrayList<String>();
        //editUserList.add("MBishop");
        //Map<String, Object> editUserListJson = new HashMap<String, Object>();
        //editUserListJson.put("users", editUserList);

        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest5 () {

        smirkService = "/listRecords";
        url = smirkHost + smirkService;
        requestUsername = "MBishop";
        requestPassword = patientPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);

        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);

        try {
            ResponseEntity<String[]> httpEntityResponse = restTemplate.exchange(url, HttpMethod.GET, postHeaders, String[].class);
            recordSummaryList = httpEntityResponse.getBody();
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        for (String  recordSummary : recordSummaryList) {
            log.debug("Recieved record from API: " + recordSummary);
        }
        // Print the API result data in the format specified by the integration test requirements
        if (recordSummaryList.length > 0) {
            recordId = recordSummaryList[0].split(",")[0];
            // print first record id
            System.out.println(recordId);
        }
        else {
            System.out.println("FAILED Test 5 - This user does not have access to list any records");
        }


        return;
    }

    private void integrationTest6 () {

        smirkService = "/viewRecord";
        url = smirkHost + smirkService;
        requestUsername = "MBishop";
        requestPassword = patientPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);

        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        SuperSetOfAllMedicalRecordTypes medRecord = null;
        try {
            ResponseEntity<SuperSetOfAllMedicalRecordTypes> httpEntityResponse = restTemplate.exchange(url + "/" + recordId,
                    HttpMethod.GET,
                    postHeaders,
                    SuperSetOfAllMedicalRecordTypes.class);
            medRecord = httpEntityResponse.getBody();
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        // Print the API result data in the format specified by the integration test requirements
        // print record, the MedicalRecord.toString() method specifies printing in the required way.

        // Print the main MedicalRecord data
        System.out.println("Record ID : " + medRecord.getMedicalRecordId());
        System.out.println("Record Type : " + medRecord.getMedicalRecordRecord_type());
        System.out.println("Record Date : " + medRecord.getMedicalRecordDate());
        System.out.println("Owner : " + medRecord.getMedicalRecordOwner());
        System.out.println("Patient : " + medRecord.getMedicalRecordPatient());
        System.out.println("Edit Permissions : " + medRecord.getMedicalRecordEdit());
        System.out.println("View Permissions : " +medRecord.getMedicalRecordView());

        // Now print the data from the record sub type
        String recordSubType = medRecord.getMedicalRecordRecord_type();
        switch (recordSubType) {
            case "Doctor Exam":
                System.out.println("Date : " + medRecord.getDoctorExamRecordExamDate());
                System.out.println("Doctor : " + medRecord.getDoctorExamRecordDoctor());
                System.out.println("Notes : " + medRecord.getDoctorExamRecordNotes());
                break;

            case "Test Result":
                //todo
                break;

            case "Diagnosis":
                //todo
                break;

            case "Insurance Claim":
                //todo
                break;

            case "Patient Doctor Correspondence":
                //todo
                break;

            case "Raw":
                //todo
                break;

            default:
                log.error("Record type not found: " + recordSubType);
        }

        return;
    }

    private void integrationTest15 () {

        smirkService = "/createInsAdmin";
        url = smirkHost + smirkService;
        requestUsername = "DMurphy";
        requestPassword = sysadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "PGarcia");
        objectNode.put("password", insadminPassword);
        objectNode.put("fname", "Penelope");
        objectNode.put("lname", "Garcia");
        objectNode.put("cname", "Vangsness Insurance");
        objectNode.put("caddress", "312124253");
        objectNode.put("address", "17000 Denechi Rd, Austin, TX 73301");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest22 () {

        smirkService = "/addRawRecord";
        url = smirkHost + smirkService;
        requestUsername = "PGarcia";
        requestPassword = insadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("id", "63481249");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("description", "License Scan");
        objectNode.put("base64EncodedBinary", "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx");
        //edit permission users
        ArrayNode editArrayNode = objectNode.putArray("edit");
        editArrayNode.add("PGarcia");
        //view permission users
        ArrayNode viewArrayNode = objectNode.putArray("view");
        viewArrayNode.add("PGarcia");
        viewArrayNode.add("MBishop");

        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest23 () {

        smirkService = "/viewRecord";
        url = smirkHost + smirkService;
        requestUsername = "PGarcia";
        requestPassword = insadminPassword;
        recordId = "63481249";

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);

        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        SuperSetOfAllMedicalRecordTypes medRecord = null;
        try {
            ResponseEntity<SuperSetOfAllMedicalRecordTypes> httpEntityResponse = restTemplate.exchange(url + "/" + recordId,
                    HttpMethod.GET,
                    postHeaders,
                    SuperSetOfAllMedicalRecordTypes.class);
            medRecord = httpEntityResponse.getBody();
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        // Print the API result data in the format specified by the integration test requirements
        // print record, the MedicalRecord.toString() method specifies printing in the required way.

        // Print the main MedicalRecord data
        System.out.println("Record ID : " + medRecord.getMedicalRecordId());
        System.out.println("Record Type : " + medRecord.getMedicalRecordRecord_type());
        System.out.println("Record Date : " + medRecord.getMedicalRecordDate());
        System.out.println("Owner : " + medRecord.getMedicalRecordOwner());
        System.out.println("Patient : " + medRecord.getMedicalRecordPatient());
        System.out.println("Edit Permissions : " + medRecord.getMedicalRecordEdit());
        System.out.println("View Permissions : " +medRecord.getMedicalRecordView());

        // Now print the data from the record sub type
        String recordSubType = medRecord.getMedicalRecordRecord_type();
        switch (recordSubType) {
            case "Doctor Exam":
                System.out.println("Date : " + medRecord.getDoctorExamRecordExamDate());
                System.out.println("Doctor : " + medRecord.getDoctorExamRecordDoctor());
                System.out.println("Notes : " + medRecord.getDoctorExamRecordNotes());
                break;

            case "Test Result":
                //todo
                break;

            case "Diagnosis":
                //todo
                break;

            case "Insurance Claim":
                //todo
                break;

            case "Patient Doctor Correspondence":
                //todo
                break;

            case "Raw":
                System.out.println("Description : " + medRecord.getRawRecordDescription());
                System.out.println("File : " + medRecord.getRawRecordBase64EncodedBinary());
                break;

            default:
                log.error("Record type not found: " + recordSubType);
        }

        return;
    }
}