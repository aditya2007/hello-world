package com.helloworld.rest.dev.annotations;

import java.util.Collection;
import java.util.List;

public interface AssetPersister {

	int createAssets(String tenantId, @Accessible Collection<String> accessibleResources,
					 @Mirror List<Asset> assets);
}
