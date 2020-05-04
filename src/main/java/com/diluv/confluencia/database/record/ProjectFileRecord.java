package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectFileRecord {

    /**
     * An internally unique identifier for the file.
     */
    private final long id;

    /**
     * The display name of the file.
     */
    private final String name;

    /**
     * A SHA 512 hash of the file.
     */
    private final String sha512;

    /**
     * The size of the file in bytes.
     */
    private final long size;

    /**
     * The change log text for the file.
     */
    private final String changelog;

    /**
     * The date the file was initially uploaded.
     */
    private final long createdAt;

    /**
     * The date the file was last modified.
     */
    private final long updatedAt;

    /**
     * Whether or not the file has been released.
     */
    private final boolean released;

    /**
     * The type of release the file is, Alpha, Beta, Release.
     */
    private final String releaseType;

    /**
     * The classifier on the file. E.g. binary, src, javadocs, deobf.
     */
    private final String classifier;

    /**
     * The file process status.
     */
    private final FileProcessingStatus processingStatus;

    /**
     * The time the file process status last changed.
     */
    private final long processingStatusChanged;

    /**
     * The id of the project that this file belongs to.
     */
    private final long projectId;

    /**
     * The project type slug the project is under.
     */
    private final String projectTypeSlug;

    /**
     * The game slug the project is under.
     */
    private final String gameSlug;

    /**
     * The id of the user who uploaded the file.
     */
    private final long userId;

    /**
     * The username of the user who uploaded the file.
     */
    private final String username;

    public ProjectFileRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.sha512 = rs.getString("sha512");
        this.size = rs.getLong("size");
        this.changelog = rs.getString("changelog");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.updatedAt = rs.getTimestamp("updated_at").getTime();
        this.released = rs.getBoolean("released");
        this.releaseType = rs.getString("release_type");
        this.classifier = rs.getString("classifier");
        this.processingStatus = FileProcessingStatus.values()[rs.getInt("processing_status")];
        this.processingStatusChanged = rs.getTimestamp("processing_status_changed").getTime();
        this.projectId = rs.getLong("project_id");
        this.projectTypeSlug = rs.getString("project_type_slug");
        this.gameSlug = rs.getString("game_slug");
        this.userId = rs.getLong("user_id");
        this.username = rs.getString("username");
    }

    public long getId () {

        return this.id;
    }

    public String getName () {

        return this.name;
    }

    public String getSha512 () {

        return this.sha512;
    }

    public long getSize () {

        return this.size;
    }

    public String getChangelog () {

        return this.changelog;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }

    public long getUpdatedAt () {

        return this.updatedAt;
    }

    public boolean isReleased () {

        return this.released;
    }

    public String getReleaseType () {

        return this.releaseType;
    }

    public String getClassifier () {

        return this.classifier;
    }

    public FileProcessingStatus getProcessingStatus () {

        return this.processingStatus;
    }

    public long getProcessingStatusChanged () {

        return this.processingStatusChanged;
    }

    public long getProjectId () {

        return this.projectId;
    }

    public String getProjectTypeSlug () {

        return this.projectTypeSlug;
    }

    public String getGameSlug () {

        return this.gameSlug;
    }

    public long getUserId () {

        return this.userId;
    }

    public String getUsername () {

        return this.username;
    }
}
