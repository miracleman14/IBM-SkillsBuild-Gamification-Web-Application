package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // Assuming "user_id" is the correct column name
    private User user;

    @Column(columnDefinition = "TEXT")
    private String avatarDataURL;

    @Column(columnDefinition = "TEXT")
    private String skinColor;

    @Column(columnDefinition = "TEXT")
    private String eyeColor;

    @Column(columnDefinition = "TEXT")
    private String hairType;

    @Column(columnDefinition = "TEXT")
    private String hairColor;

    @Column(columnDefinition = "TEXT")
    private String noseSize;

    @Column(columnDefinition = "TEXT")
    private String mouthSize;

    @Column(columnDefinition = "BOOLEAN")
    private boolean glasses;

    // Constructors
    public Avatar() {
    }

    public Avatar(String avatarDataURL, String skinColor, String eyeColor, String hairType,
        String hairColor, String noseSize, String mouthSize, boolean glasses) {
        this.avatarDataURL = avatarDataURL;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.hairType = hairType;
        this.hairColor = hairColor;
        this.noseSize = noseSize;
        this.mouthSize = mouthSize;
        this.glasses = glasses; // Assign glasses in constructor
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAvatarDataURL() {
        return avatarDataURL;
    }

    public void setAvatarDataURL(String avatarDataURL) {
        this.avatarDataURL = avatarDataURL;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getNoseSize() {
        return noseSize;
    }

    public void setNoseSize(String noseSize) {
        this.noseSize = noseSize;
    }

    public String getMouthSize() {
        return mouthSize;
    }

    public void setMouthSize(String mouthSize) {
        this.mouthSize = mouthSize;
    }

    public boolean isGlasses() {
        return glasses;
    }

    public void setGlasses(boolean glasses) {
        this.glasses = glasses;
    }

    // toString method for debugging/logging
    @Override
    public String toString() {
        return "Avatar{" +
            "id=" + id +
            ", avatarDataURL='" + avatarDataURL + '\'' +
            ", skinColor='" + skinColor + '\'' +
            ", eyeColor='" + eyeColor + '\'' +
            ", hairType='" + hairType + '\'' +
            ", hairColor='" + hairColor + '\'' +
            ", noseSize='" + noseSize + '\'' +
            ", mouthSize='" + mouthSize + '\'' +
            ", glasses=" + glasses +
            '}';
    }
}
