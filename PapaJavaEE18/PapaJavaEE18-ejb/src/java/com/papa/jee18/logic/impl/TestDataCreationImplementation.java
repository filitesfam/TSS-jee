/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic.impl;

import com.papa.jee18.dao.HolidayAccess;
import com.papa.jee18.dao.HolidayCountry;
import com.papa.jee18.dao.HolidayRegion;
import com.papa.jee18.dao.PersonAccess;
import com.papa.jee18.entities.HolidayType;
import com.papa.jee18.entities.LanguageType;
import com.papa.jee18.entities.PersonEntity;
import java.time.LocalDate;
import javax.ejb.EJB;
import com.papa.jee18.logic.TestDataCreationLogic;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;


@Stateless
public class TestDataCreationImplementation implements TestDataCreationLogic {

    @EJB
    private PersonAccess pa;

    @EJB
    private HolidayAccess ha;

    @Resource
    private EJBContext ejbContext;

    public void createAllHolidays() {

        LocalDate localDate = LocalDate.of(2018, 10, 3);
        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2018, 10, 3);
        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2018, 11, 1);
        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2018, 11, 1);
        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2018, 12, 25);
        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2018, 12, 25);
        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2018, 12, 26);
        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2018, 12, 26);
        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        /////
        localDate = LocalDate.of(2020, 1, 1);
        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 1, 1);
        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 4, 19);
        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 4, 19);
        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 4, 22);
        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 4, 22);
        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 5, 1);
        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 5, 1);
        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 5, 30);
        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 5, 30);
        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 6, 10);
        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 6, 10);
        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 6, 20);
        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 6, 20);
        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 3, 10);
        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 3, 10);
        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 11, 1);
        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 11, 1);
        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 12, 25);
        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 12, 25);
        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2020, 12, 26);
        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        localDate = LocalDate.of(2020, 12, 26);
        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        /////

        localDate = LocalDate.of(2019, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2019, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        ////
        localDate = LocalDate.of(2021, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2021, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        ////
        localDate = LocalDate.of(2022, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2022, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        ////
        localDate = LocalDate.of(2023, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2023, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        /////
        localDate = LocalDate.of(2024, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2024, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        ////
        localDate = LocalDate.of(2025, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2025, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        /////
        localDate = LocalDate.of(2026, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2026, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        //////
        localDate = LocalDate.of(2027, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2027, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        ////
        localDate = LocalDate.of(2028, 1, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Neujahrstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 1, 1);

        ha.createHoliday(localDate, LanguageType.EN, "New Year's Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 4, 19);

        ha.createHoliday(localDate, LanguageType.DE, "Karfreitag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 4, 19);

        ha.createHoliday(localDate, LanguageType.EN, "Good Friday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 4, 22);

        ha.createHoliday(localDate, LanguageType.DE, "Ostermontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 4, 22);

        ha.createHoliday(localDate, LanguageType.EN, "Easter Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 5, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Arbeit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 5, 1);

        ha.createHoliday(localDate, LanguageType.EN, "Labour Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 5, 30);

        ha.createHoliday(localDate, LanguageType.DE, "Christi Himmelfahrt", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 5, 30);

        ha.createHoliday(localDate, LanguageType.EN, "Ascension Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 6, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Pfingstmontag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 6, 10);

        ha.createHoliday(localDate, LanguageType.EN, "Whit Monday", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 6, 20);

        ha.createHoliday(localDate, LanguageType.DE, "Fronleichnam", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 6, 20);

        ha.createHoliday(localDate, LanguageType.EN, "orpus Christi", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 3, 10);

        ha.createHoliday(localDate, LanguageType.DE, "Tag der Deutschen Einheit", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 3, 10);

        ha.createHoliday(localDate, LanguageType.EN, "German Unity Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 11, 1);

        ha.createHoliday(localDate, LanguageType.DE, "Allerheiligen", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 11, 1);

        ha.createHoliday(localDate, LanguageType.EN, "All Saints' Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 12, 25);

        ha.createHoliday(localDate, LanguageType.DE, "Weihnachtstag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 12, 25);

        ha.createHoliday(localDate, LanguageType.EN, "Christmas Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 12, 26);

        ha.createHoliday(localDate, LanguageType.DE, "Zweiter Weihnachtsfeiertag", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

        localDate = LocalDate.of(2028, 12, 26);

        ha.createHoliday(localDate, LanguageType.EN, "Boxing Day", HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);

    }

    @Override
    public void createPersons() {
        PersonEntity employee1 = pa.createPerson("employee1" + "@acme.edu");
        employee1.setFirstName("emp1 Name");
        employee1.setLastName("Surname");
        employee1.setDateOfBirth(LocalDate.of(1988, 10, 15));

        PersonEntity employee2 = pa.createPerson("employee2" + "@acme.edu");
        employee2.setFirstName("emp2 Name");
        employee2.setLastName("Surname");
        employee2.setDateOfBirth(LocalDate.of(1980, 5, 15));

        PersonEntity secretary1 = pa.createPerson("secretary1" + "@acme.edu");
        secretary1.setFirstName("sec1 Name");
        secretary1.setLastName("Surname");
        secretary1.setDateOfBirth(LocalDate.of(1988, 10, 15));

        PersonEntity secretary2 = pa.createPerson("secretary2" + "@acme.edu");
        secretary2.setFirstName("sec2 Name");
        secretary2.setLastName("Surname");
        secretary2.setDateOfBirth(LocalDate.of(1980, 5, 15));
        
        PersonEntity supervisor1 = pa.createPerson("supervisor1" + "@acme.edu");
        supervisor1.setFirstName("supervisor1 Name");
        supervisor1.setLastName("Surname");
        supervisor1.setDateOfBirth(LocalDate.of(1980, 5, 15));
        
        PersonEntity supervisor2 = pa.createPerson("supervisor2" + "@acme.edu");
        supervisor2.setFirstName("supervisor2 Name");
        supervisor2.setLastName("Surname");
        supervisor2.setDateOfBirth(LocalDate.of(1980, 5, 15));
        
        PersonEntity assistant1 = pa.createPerson("assistant1" + "@acme.edu");
        assistant1.setFirstName("assistant1 Name");
        assistant1.setLastName("Surname");
        assistant1.setDateOfBirth(LocalDate.of(1980, 5, 15));
        
        PersonEntity assistant2 = pa.createPerson("assistant2" + "@acme.edu");
        assistant2.setFirstName("assistant2 Name");
        assistant2.setLastName("Surname");
        assistant2.setDateOfBirth(LocalDate.of(1980, 5, 15));
        
    }

    @Override
    public void createHolidays() {
        createAllHolidays();
    }

}
