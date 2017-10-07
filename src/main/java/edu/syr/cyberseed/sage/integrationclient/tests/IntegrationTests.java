package edu.syr.cyberseed.sage.integrationclient.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.syr.cyberseed.sage.integrationclient.entities.*;
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
import java.lang.reflect.Array;
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
        integrationTest7();
        integrationTest8();
        integrationTest9();
        integrationTest10();
        integrationTest11();
        integrationTest12();
        //integrationTest13();
        //integrationTest14();
        integrationTest15();
        //integrationTest16();
        //integrationTest17();
        integrationTest18();
        integrationTest19();
        integrationTest20();
        integrationTest21();
        integrationTest22();
        integrationTest23();
        //integrationTest24();
        //integrationTest25();
        //integrationTest26();
        //integrationTest27();
        integrationTest28();

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

            case "Patient Doctor Correspondence Record":
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

    private void integrationTest7 () {
        String a[];
        smirkService = "/viewRecoveryPhrase";
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

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);
        Doctor d = new Doctor();
        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        try {
            ResponseEntity<Doctor> httpEntityResponse = restTemplate.exchange(url + "/" + "KLibby",
                    HttpMethod.GET,
                    postHeaders,
                    Doctor.class);
            d=httpEntityResponse.getBody();
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

        System.out.println(d.getRphrase());

        // Print the API result data in the format specified by the integration test requirements
        // print record, the MedicalRecord.toString() method specifies printing in the required way.

        // Print the main MedicalRecord date

        return;
    }

    // Integration test 8
    private void integrationTest8 () {

        smirkService = "/addDiagnosisRecord";
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
        objectNode.put("id", "832942");
        objectNode.put("date", "2017-09-07T00:00:00.000Z");
        objectNode.put("diagnosisDate", "2017-04-09T00:00:00.000Z");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("doctorUsername", "KLibby");
        objectNode.put("diagnosis", "Positive for The Sting");
        //edit permission users
        ArrayNode editArrayNode = objectNode.putArray("edit");
        editArrayNode.add("KLibby");
        //view permission users
        ArrayNode viewArrayNode = objectNode.putArray("view");
        viewArrayNode.add("KLibby");

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

        return;
    }
//    end of test 8

    //    begining of test 9
    private void integrationTest9 () {

        smirkService = "/viewRecord";
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

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);

        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        SuperSetOfAllMedicalRecordTypes medRecord = null;
        try {
            ResponseEntity<SuperSetOfAllMedicalRecordTypes> httpEntityResponse = restTemplate.exchange(url + "/" + "832942",
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
                //todo
                break;

            case "Test Result":
                //todo
                break;

            case "Diagnosis Record":
                System.out.println("Date : " + medRecord.getDiagnosisRecordDate());
                System.out.println("Doctor : " + medRecord.getDiagnosisRecordDoctor());
                System.out.println("Notes : " + medRecord.getDiagnosisRecordDiagnosis());
                break;

            case "Insurance Claim":
                //todo
                break;

            case "Patient Doctor Correspondence Record":
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
    //    end of test 9

    private void integrationTest10 () {

        smirkService = "/createNurse";
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
        objectNode.put("username", "KFlynn");
        objectNode.put("password", nursePassword);
        objectNode.put("fname", "Kevin");
        objectNode.put("lname", "Flynn");
        objectNode.put("pname", "The Gibson Associates");
        objectNode.put("paddress", "1983 Aburn Road, Chicago, IL 60007");
        objectNode.put("adoctors", "KLibby");
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
    private void integrationTest11 () {

        smirkService = "/createMedAdmin";
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
        objectNode.put("username", "DLightman");
        objectNode.put("password", medadminPassword);
        objectNode.put("fname", "David");
        objectNode.put("lname", "Lightman");
        objectNode.put("pname", "Vangsness Insurance");
        objectNode.put("paddress", "1983 Aburn Road, Chicago, IL 60007");
        objectNode.put("adoctor", "KLibby");
        objectNode.put("anurse", "KFlynn");
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

    private void integrationTest12 () {

        smirkService = "/viewPatientProfile";
        url = smirkHost + smirkService;
        requestUsername = "DLightman";
        requestPassword = medadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);
        User_patient u = new User_patient();
        ArrayList<String> a = new ArrayList<String>();
        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        try {
            ResponseEntity<ArrayList> httpEntityResponse = restTemplate.exchange(url + "/" + "MBishop",
                    HttpMethod.GET,
                    postHeaders,
                    ArrayList.class);
            a=httpEntityResponse.getBody();
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

        System.out.println("Username : "+a.get(0));
        System.out.println("Roles : "+a.get(3));
        System.out.println("Permissions : "+u.getPermissions());
        System.out.println("First Name : "+a.get(1));
        System.out.println("Last Name : "+a.get(2));
        System.out.println("DOB : "+a.get(6));
        System.out.println("SSN : "+a.get(5));
        System.out.println("Address : "+a.get(4));


        // Print the API result data in the format specified by the integration test requirements
        // print record, the MedicalRecord.toString() method specifies printing in the required way.

        // Print the main MedicalRecord date

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

    private void integrationTest18 () {

        smirkService = "/createCorrespondenceRecord";
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
        objectNode.put("id", "7753571");
        objectNode.put("note_date", "2017-08-14T12:00:00.000Z");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("doctorUsername", "KLibby");
        //edit permission users
        ArrayNode editArrayNode = objectNode.putArray("edit");
        editArrayNode.add("KLibby");
        editArrayNode.add("MBishop");
        //view permission users
        ArrayNode viewArrayNode = objectNode.putArray("view");
        viewArrayNode.add("KLibby");
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

    private void integrationTest19 () {

        smirkService = "/addCorrespondenceNote";
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
        objectNode.put("id", "7753571");
        objectNode.put("note_date", "2017-08-14T12:00:00.000Z");
        objectNode.put("note_text", "Change bandage daily.");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("doctorUsername", "KLibby");

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

    private void integrationTest20 () {

        smirkService = "/addCorrespondenceNote";
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
        objectNode.put("id", "7753571");
        objectNode.put("note_date", "2017-08-15T12:00:00.000Z");
        objectNode.put("note_text", "Does this look infected?");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("doctorUsername", "KLibby");

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

    private void integrationTest21 () {

        smirkService = "/viewRecord";
        url = smirkHost + smirkService;
        requestUsername = "KLibby";
        requestPassword = doctorPassword;

        recordId = "7753571";

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
            case "Patient Doctor Correspondence Record":
                CorrespondenceRecord[] correspondenceRecordNoteList = medRecord.getCorrespondenceRecordList();
                if (correspondenceRecordNoteList != null) {
                    System.out.println("Notes:");
                    for (CorrespondenceRecord note : correspondenceRecordNoteList) {
                        if (note.getNote_date() != null) {
                            System.out.println("        Date: " + note.getNote_date());
                        }
                        if (note.getNote_text() != null) {
                            System.out.println("        Note: " + note.getNote_text());
                        }
                    }
                }
                else {
                    log.debug("correspondenceRecordNoteList is empty");
                }
                break;
            default:
                log.error("Record type not found: " + recordSubType);
        }

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

            case "Patient Doctor Correspondence Record":
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

    private void integrationTest28 () {

        smirkService = "/viewPatientProfile";
        url = smirkHost + smirkService;
        requestUsername = "DLightman";
        requestPassword = medadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);
        User_patient u = new User_patient();
        ArrayList<String> a = new ArrayList<String>();
        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        try {
            ResponseEntity<ArrayList> httpEntityResponse = restTemplate.exchange(url + "/" + "MBishop",
                    HttpMethod.GET,
                    postHeaders,
                    ArrayList.class);
            a=httpEntityResponse.getBody();
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

        System.out.println("Username : "+a.get(0));
        System.out.println("Roles : "+a.get(3));
        System.out.println("Permissions : "+u.getPermissions());
        System.out.println("First Name : "+a.get(1));
        System.out.println("Last Name : "+a.get(2));
        System.out.println("DOB : "+a.get(6));
        System.out.println("SSN : "+a.get(5));
        System.out.println("Address : "+a.get(4));


        // Print the API result data in the format specified by the integration test requirements
        // print record, the MedicalRecord.toString() method specifies printing in the required way.

        // Print the main MedicalRecord date

        return;
    }
}