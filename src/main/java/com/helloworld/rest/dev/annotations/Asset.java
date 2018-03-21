package com.helloworld.rest.dev.annotations;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface Asset {

		String getAssetType();

		JsonNode getSuperTypes();

		List<String> getSuperTypesArray();

		String getParentId();

		JsonNode getAttributes();

		JsonNode getGeolocation();

		JsonNode getAncestors();

		List<String> getAncestorsArray();
}
