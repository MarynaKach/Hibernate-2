package entity;

import static java.util.Objects.isNull;
import static org.hibernate.annotations.common.util.StringHelper.isEmpty;


public enum Feature {
    TRAILER ("Trailers"),
    COMMENTARIES ("Commentaries"),
    DELETE_SCENES ("Deleted Scenes"),
    BEHIND_THE_SCENES ("Behind the Scenes");

    private String value;

    Feature(String featureName) {
        this.value = featureName;
    }

    public String getValue() {
        return value;
    }
    public static Feature featureByValue(String value){
        if (isNull(value) || value.isEmpty()){
            return null;
        }
        for (Feature feature : Feature.values()) {
            if (feature.value.equals(value)) {
                return feature;
            }
        }
        return null;
    }
}

