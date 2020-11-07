/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.HolidayEntity;
import com.papa.jee18.entities.HolidayType;
import com.papa.jee18.entities.LanguageType;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class HolidayAccess extends BaseEntityAccess<HolidayEntity> {

    public HolidayAccess() {
        super(HolidayEntity.class);
    }

    public HolidayEntity getHoliday(String uuid) {
        return getBaseEntityAccessByUuid(uuid);

    }

    public HolidayEntity createHoliday(LocalDate holidayDate, LanguageType languageType, String text, HolidayType type,
            HolidayCountry country, HolidayRegion region) {
        HolidayEntity entity = HolidayEntity.newInstance();
        entity.setHolidayDate(holidayDate);
        entity.setLanguageType(languageType);
        entity.setText(text);
        entity.setType(type);
        entity.setCountry(country);
        entity.setRegion(region);

        em.persist(entity);
        return entity;
    }

    public HolidayEntity getHoliday(LocalDate holidayDate, LanguageType languageType, HolidayType type,
            HolidayCountry country) {
        try {
            return em.createNamedQuery("HolidayEntity.getEntityByDate", HolidayEntity.class)
                    .setParameter("holidayDate", holidayDate)
                    .setParameter("languageType", languageType)
                    .setParameter("type", type)
                    .setParameter("country", country)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public HolidayEntity getHoliday(LocalDate holidayDate, LanguageType languageType, HolidayType type,
            HolidayCountry country, HolidayRegion region) {
        try {
            return em.createNamedQuery("HolidayEntity.getEntityByDateAndRegion", HolidayEntity.class)
                    .setParameter("holidayDate", holidayDate)
                    .setParameter("languageType", languageType)
                    .setParameter("type", type)
                    .setParameter("country", country)
                    .setParameter("region", region)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HolidayEntity> getHolidayList(LocalDate startholidayDate, LocalDate endholidayDate, LanguageType languageType, HolidayType type,
            HolidayCountry country, HolidayRegion region) {
        try {
            return em.createNamedQuery("HolidayEntity.getEntityByCriterias", HolidayEntity.class)
                    .setParameter("startholidayDate", startholidayDate)
                    .setParameter("endholidayDate", endholidayDate)
                    .setParameter("languageType", languageType)
                    .setParameter("type", type)
                    .setParameter("country", country)
                    .setParameter("region", region)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
