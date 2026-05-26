/*
 * GameBox
 * Copyright (C) 2019  Niklas Eicker
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.nikl.gamebox.module.cloud;

import me.nikl.gamebox.GameBox;
import me.nikl.gamebox.data.database.DataBase;
import me.nikl.gamebox.exceptions.module.CloudModuleNotFoundException;
import me.nikl.gamebox.exceptions.module.CloudModuleVersionNotFoundException;
import me.nikl.gamebox.exceptions.module.GameBoxCloudException;
import me.nikl.gamebox.module.data.CloudModuleData;
import me.nikl.gamebox.module.data.CloudModuleDataWithVersions;
import me.nikl.gamebox.module.data.VersionedCloudModule;
import me.nikl.gamebox.module.local.LocalModule;
import me.nikl.gamebox.utility.versioning.SemanticVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Niklas Eicker
 */
public class CloudService {
    private GameBox gameBox;
    private long lastCloudContentUpdate = 0L;
    private Map<String, CloudModuleData> cloudContent = new HashMap<>();

    public CloudService(GameBox gameBox) {
        this.gameBox = gameBox;
    }

    /**
     * Cache the modules currently offered by the GameBox API
     *
     * Do not call this function on the main thread
     */
    public void cacheCloudContent() throws GameBoxCloudException {
        lastCloudContentUpdate = System.currentTimeMillis();
        cloudContent.clear();
    }

    public CloudModuleData getCachedModuleData(String moduleID) throws GameBoxCloudException {
        CloudModuleData cloudModuleData = cloudContent.get(moduleID);
        if (cloudModuleData == null) throw new CloudModuleNotFoundException("No moduledata found for ID '" + moduleID + "'");
        return cloudModuleData;
    }

    /**
     * Cache the modules currently offered by the GameBox API
     *
     * Do not call this function on the main thread
     */
    public CloudModuleDataWithVersions getCloudModuleDataWithVersions(String moduleId) throws GameBoxCloudException {
        throw new CloudModuleNotFoundException("Cloud modules are disabled");
    }

    public boolean hasCachedUpdate(LocalModule localModule) {
        return false;
    }

    public void downloadModule(VersionedCloudModule module, DataBase.Callback<LocalModule> callback) {
        callback.onFailure(new GameBoxCloudException("Cloud module downloads are disabled"), null);
    }

    /**
     * Get the versioned cloud module data from the GameBox API
     *
     * Do not call this function on the main thread
      */
    public VersionedCloudModule getVersionedCloudModule(String moduleId, SemanticVersion version) throws GameBoxCloudException {
        throw new CloudModuleVersionNotFoundException("Cloud modules are disabled");
    }

    public List<CloudModuleData> getCachedCloudContent() {
        return new ArrayList<>(this.cloudContent.values());
    }

    public long secondsSinceLastCloudContentCacheUpdate() {
        return Math.round((System.currentTimeMillis() - lastCloudContentUpdate)/1000.);
    }
}
