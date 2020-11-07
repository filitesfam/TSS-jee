/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import com.papa.jee18.dao.HolidayCountry;
import com.papa.jee18.dao.HolidayRegion;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "HOLIDAY")
@NamedQueries({
    @NamedQuery(
            name = "HolidayEntity.getEntityByUUID",
            query = "SELECT e FROM HolidayEntity e WHERE e.uuid = :uuid"
    )
    ,
    @NamedQuery(
            name = "HolidayEntity.getEntityByDateAndRegion",
            query = "SELECT e FROM HolidayEntity e "
            + "WHERE e.holidayDate = :holidayDate "
            + "AND e.languageType = :languageType "
            + "AND e.type = :type "
            + "AND e.country = :country "
            + "AND e.region = :region "
    )
    ,
    @NamedQuery(
            name = "HolidayEntity.getEntityByDate",
            query = "SELECT e FROM HolidayEntity e "
            + "WHERE e.holidayDate = :holidayDate "
            + "AND e.languageType = :languageType "
            + "AND e.type = :type "
            + "AND e.country = :country "
    )
    ,
    @NamedQuery(
            name = "HolidayEntity.getEntityByCriterias",
            query = "SELECT e FROM HolidayEntity e "
            + "WHERE e.holidayDate >= :startholidayDate "
            + "AND e.holidayDate <= :endholidayDate "
            + "AND e.languageType = :languageType "
            + "AND e.type = :type "
            + "AND e.country = :country "
            + "AND e.region = :region "
    )

})
public class HolidayEntity extends BaseEntity {

    private static final long serialVersionUID = -3767765242992243529L;

    @Column(name = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @Enumerated(EnumType.STRING)
    private LanguageType languageType;

    @Column(name = "HOLIDAYDATE")
    private LocalDate holidayDate;

    @Enumerated(EnumType.STRING)
    private HolidayCountry country;

    @Enumerated(EnumType.STRING)
    private HolidayRegion region;

    @Transient
    private int dayOfWeek;

    public HolidayEntity() {
        this(false);
    }

    HolidayEntity(boolean isNew) {
        super(isNew);
    }

    public static HolidayEntity newInstance() {
        return new HolidayEntity(true);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HolidayType getType() {
        return type;
    }

    public void setType(HolidayType type) {
        this.type = type;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public HolidayCountry getCountry() {
        return country;
    }

    public void setCountry(HolidayCountry country) {
        this.country = country;
    }

    public HolidayRegion getRegion() {
        return region;
    }

    public void setRegion(HolidayRegion region) {
        this.region = region;
    }

    public int getDayOfWeek() {
        return this.holidayDate.getDayOfWeek().getValue();
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
