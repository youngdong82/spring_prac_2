package spring_yd.prac_2.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MemberType {
    ADMIN, PLANE;

    @JsonCreator
    public static MemberType convert(String type){
        for(MemberType testEnum : MemberType.values()){
            if(testEnum.name().equals(type)){
                return testEnum;
            }
        }
        return null;
    }
}
