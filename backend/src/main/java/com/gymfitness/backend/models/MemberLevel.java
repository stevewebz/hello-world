package com.gymfitness.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "memberlevels")
public class MemberLevel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long LevelId;
    private String LevelName;

    @OneToOne(mappedBy = "MemberLevel")
    private Member Member;

    public MemberLevel(){
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return Member;
    }
    /**
     * @param member the member to set
     */
    public void setMember(Member member) {
        Member = member;
    }

    /**
     * @return the levelId
     */
    public Long getLevelId() {
        return LevelId;
    }
    /**
     * @param levelId the levelId to set
     */
    public void setLevelId(Long levelId) {
        LevelId = levelId;
    }

    /**
     * @return the levelName
     */
    public String getLevelName() {
        return LevelName;
    }
    /**
     * @param levelName the levelName to set
     */
    public void setLevelName(String levelName) {
        LevelName = levelName;
    }
}