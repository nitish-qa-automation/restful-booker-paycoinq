package com.heroukapp.apis.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Component
public class JsonUtil {
    public  JSONObject             jsonObject;
    private             File                   jsonFile;
    public JSONObject getJSON() { return jsonObject; }

    public static JSONObject readJSON(File file) {
        InputStream is = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            fail("File not found.");
        }

        JSONTokener jsonTokener = new JSONTokener(is);
        return new JSONObject(jsonTokener);
    }


}
