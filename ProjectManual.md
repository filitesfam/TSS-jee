## TSS REQUIREMENTS
## GENERAL CONVENTIONS
TSS requirements contain an obligation keyword. This is one of:

1. **SHALL** - denotes a mandatory requirement that has to be realized.
2. **SHOULD** - denotes an optional requirement that has a certain value - it may be skipped if serious obstacles impede the realization.
3. **WILL** - denotes an interface requirement that has to be taken into account but the realization of the functionality is outside of the system.

Regarding the examination and grading of the course project, there is a minimum set of requirements
that have to be realized to pass (grade 4.0 „ausreichend“). Those requirements are labeled
with *MIN*.

## USE CASES AND ROLES

**Roles**

**Employee**
An employee can be a student or a university staff member.

**Supervisor**
A supervisor is a university staff member. The supervisor is the contractual boss of an
employee. Supervisors have to sign the time sheets prior to archiving.

**Assistant**
An assistant is a university staff member. Assistants are responsible for the concrete tasks
assigned to employees. Assistants may as well manage contracts in the TSS.

**Secretary**
A secretary is a university staff member. Secretaries are responsible for printing the time
sheets. They forward the time sheets to the supervisor in order to sign them. Secretaries
are also responsible for archiving the signed time sheets.

**Guest**
Guests may only view public information and documentation about the TSS.

**Administrator**
An Administrator is a university staff member. Administrators are responsible to install,
configure, and operate the TSS. As TSS users, they may get additional responsibilities, e.g.
deleting contracts of former employees or database management tasks.

## COMPLETED FUNCTIONAL REQUIREMENTS

**Contracts**

1. **CN1 *MIN*:** The TSS SHALL provide assistants and supervisors with the ability to manage contracts. Manage means „CRUD“ (Create, Read, Update, Delete). Update and deletion of contracts is only possible in state PREPARED.
2. **CN3 *MIN*:** The TSS SHALL provide employees, assistants, supervisors, and secretaries with the ability to view contract statistics (e.g. sum of hours due, balance).
3. **CN4 *MIN*:** The TSS SHALL calculate the total hours due, the vacation hours, the remaining hours due and take into account weekends and public holidays.

3. **CN4a *MIN*:** The TSS SHALL calculate the vacation hours by the following formula: vacationHours = vacationDaysPerYear * durationOfContract / 12 * hoursPerWeek / workingDaysPerWeek
 The duration of the contract is counted in months.
 
3. **CN4b *MIN*:** The TSS SHALL calculate the total hours due for the contract as the sum of the hours due of the individual Timesheets.

3. **CN4c *MIN*:** The TSS SHALL calculate the hours due for a Timesheet by the following formula: hoursDue = (workingDaysInPeriod - publicHolidaysInPeriod) * hoursPerWeek / workingDaysPerWeek
The period can be a week or a month, depending on the TimesheetFreqeuency of the contract.
E.g., if workingDaysPerWeek = 5, Monday to Friday count as working days, Saturday and Sunday as non-working days.
Only public holidays have to be counted that are on working days.

3. **CN4d *MIN*:** The TSS SHALL be able to determine the public holidays in Rhineland-Palatinate starting from January 01 2018 until December 31 2028 (at least).
4. **CN5 *MIN*:** The TSS SHALL set a contract to PREPARED status as soon as it is created.
5. **CN6 *MIN*:** The TSS SHALL ensure that start date, end date, frequency, hours per week, vacation hours, working days per week, and vacation days per year can only be changed when the contract is in PREPARED status.

6. **CN6a	:** The TSS SHALL ensure that start date and end date of a contract always denote complete months (i.e. the start date must be the first day of a month, the end date the last day).

7. **CN7 *MIN*:** The TSS SHALL provide assistants and supervisors with the ability to start a contract. Then, the contract changes from PREPARED to STARTED status. All Timesheets are created in status IN_PROGRESS.
8. **CN8	:** The TSS SHALL provide assistants and supervisors with the ability to terminate a started contract. Then, the contract changes from STARTED to ABORTED status.
9. **CN9 *MIN*:** The TSS SHALL ensure that only contracts can be aborted whose time sheets are in status SIGNED_BY_SUPERVISOR or IN_PROGRESS.
10. **CN10	:** The TSS SHALL record the date of termination of a contract.
11. **CN12 *MIN*:** The TSS SHALL set a contract to status ARCHIVED as soon as all time sheets of that ontract are in status ARCHIVED.

**Time Sheets**

1. **TS1 *MIN*:** The TSS SHALL create all time sheets for a contract based on the time sheet frequency and the start and end dates of the contract as soon as the contract enters the STARTED status.
2. **TS2 *MIN*:** The TSS SHALL ensure that time sheet entries can only be added, changed, and removed when the time sheet is in IN_PROGRESS status and the contract of the time sheet is in STARTED status.
3. **TS3 *MIN*:** The TSS SHALL ensure that the total hours reported in entries of type VACATION can not exceed the total vacation hours (see requirement CN4a).
4. **TS4    :** The TSS SHALL delete time sheets in status IN_PROGRESS as soon as the contract status changes to TERMINATED.
5. **TS5 *MIN*:** The TSS SHALL not delete time sheets that are in the SIGNED_BY_EMPLOYEE state.
6. **TS6 *MIN*:** The TSS SHALL not delete time sheets that are in the SIGNED_BY_SUPERVISOR state.
7. **TS7 *MIN*:** The TSS SHALL provide employees, assistants, supervisors, and secretaries with the ability to view time sheets.
8. **TS8    :** The TSS SHALL provide secretaries with the ability to print time sheets.
9. **TS9 *MIN*:** The TSS SHALL provide employees with the ability to manage their time sheet entries. Changes to entries are only allowed when a Timesheet is IN_PROGRESS.

**Signatures**

1. **SG1 *MIN*:** The TSS SHALL provide employees with the ability to sign a time sheet. Then, the time sheet changes from IN_PROGRESS to SIGNED_BY_EMPLOYEE status.
2. **SG1a   :** The TSS SHALL provide employees with the ability to revoke the signature. Then, the time sheet changes from SIGNED_BY_EMPLOYEE to IN_PROGRESS status.
3. **SG2 *MIN*:** The TSS SHALL provide supervisors with the ability to sign a time sheet that is in status SIGNED_BY_EMPLOYEE. Then, the time sheet changes to SIGNED_BY_SUPERVISOR status.
4. **SG3    :** The TSS SHALL provide assistants and supervisors with the ability to request changes to a time sheet that is in status SIGNED_BY_EMPLOYEE. Then, the time sheet changes to IN_PROGRESS status.

**Reminders**

1. **RE1 *MIN*:** On the last day of a time sheet (either end of week or end of month), the TSS SHALLsend a reminder mail to the employee if the time sheet is in state IN_PROGRESS.
2. **RE2    :** The TSS SHALL send a reminder mail to the supervisor and the assistants if the timesheet is in state SIGNED_BY_EMPLOYEE. (then, the supervisor may reject or sign thetime sheet)
3. **RE4    :** The TSS SHALL repeat reminders every day.
4. **RE5    :** The TSS SHOULD collect all reminders so that a person receives at most one e-mail perday.

**Archiving**

1. **AR1 *MIN*:** The TSS SHALL provide secretaries with the ability to archive time sheets that are in statusSIGNED_BY_SUPERVISOR. Then, the time sheet changes to status ARCHIVED.
2. **AR2 *MIN*:** The TSS SHALL ensure that archived time sheets can not be changed.

## COMPLETED NON-FUNCTIONAL REQUIREMENTS

**Access Control**

1. AC1 *MIN*:** The TSS SHALL authenticate users prior to giving access to any data.
2. AC2      :** The TSS SHALL be able to determine whether a person is university staff member.
3. AC3 *MIN*:** The TSS SHALL be designed in a way that only authorized users may view/change/delete data. Access rules are based on the users affiliation (staff, student) as well as on the roles a user owns with respect to a contract.

**User Interface**

1. **UI1 *MIN*:** The TSS SHALL be designed in a way that the most frequent task (report work) is accessible immediately after successful login.
2. **UI2    :** The TSS SHOULD support mobile devices.
3. **UI3    :** The TSS SHALL be designed such that it can be used with different browsers. At least, FireFox, Safari, Chrome should be supported.
4. **UI4    :** The TSS SHOULD use the Bootstrap CSS library.

**Internationalization**

1. **IN1 *MIN*:** The TSS SHALL be designed such that the user interface language can be switched.
2. **IN3    :** The TSS SHALL support at least two user interface languages.
3. **IN4 *MIN*:** The TSS SHALL support English as user interface language.
4. **IN5    :** The TSS SHALL support German as user interface language.

**Software Architecture**

1. **SA1 *MIN*:** The TSS SHALL be implemented according to the layered architecture (see tss-architecture document).
2. **SA2 *MIN*:** The TSS SHALL contain (at least) two modules, the web module and the EJB module.
3. **SA3 *MIN*:** The TSS team SHALL ask for consent prior to using third-party libraries that were not presented in the lecture/lab. Usage is permitted only after negotiation with the customer.
4. **SA4 *MIN*:** The TSS SHALL use one of the database servers MySQL (preferred), PostgreSql, or JavaDB/Derby.
5. **SA5 *MIN*:** The project team SHALL document architectural decisions, e.g. which subsystems do exist, how is the source code structured, etc. in the project manual.
6. **SA6 *MIN*:** The project team SHALL prefix all global names with its team name. Such names are e.g. project name, context path (root URL), database name, persistence units, server resources, etc. This is to avoid naming conflicts, since the customer has to install the systems of many teams side-by-side.

**Project Manual**

1. **PM1 *MIN*:** The TSS SHALL be documented in a project manual (can be online, e.g. Wiki) such that the customer can install the system on a fresh Payara server.
2. **PM2 *MIN*:** The project manual SHALL contain a list of completed/missed requirements.
3. **PM3 *MIN*:** The project manual SHOULD contain descriptions of problems that occurred during the implementation and their solutions.
4. **PM4 *MIN*:** Decisions made to detail and/or change requirements SHALL be documented in the project manual.
5. **PM5    :** The project participants SHOULD record the time spent on the project.

---

## MISSED FUNCTIONAL REQUIREMENTS

**Contracts**

1. **CN2 		:** The TSS SHALL provide secretaries with the ability to print contracts. This printout is intended to be used as cover page for the paper archive of the printed time sheets.
2. **CN4e		:** The TSS SHALL be able to determine the public holidays in Germany from January 01 2018 until December 31 2028 (at least).
Then, the federal state must be configurable since there are different regulations for public
holidays in the various parts of the country.
3. **CN11	:** Before terminating a contract, the TSS SHOULD warn the user if there are time sheets in state IN_PROGRESS that have entries. The user may then decide to not terminate the contract yet.

**Signatures**

1. **SG4    :** The TSS SHOULD provide employees and supervisors with the ability to digitally sign the time sheets.

**Reminders**

1. **RE3    :** The TSS SHALL send a reminder mail to the secretaries if the time sheet is in stateSIGNED_BY_SUPERVISOR.

**Archiving**

1. **AR3      :** The TSS SHALL delete time sheets 2 years after the signature of the supervisor. When alltime sheets of a contract are deleted, the contract has to be deleted as well.
2. **AR4      :** The TSS SHOULD support variable archive durations. In this case, the 2 years duration isnot fixed, but must be stored per contract. The duration should have a default value of 2 years.

## MISSED NON-FUNCTIONAL REQUIREMENTS

**Internationalization**

1. **IN2    :** The TSS SHALL provide the users to choose their language.
2. **IN6    :** The TSS SHOULD send reminders to users in their preferred language.

---
## PM1 MIN : Payara configuration

## Define JDBC Connection Pool

Click **Payara Server Console → Resources → JDBC → JDBC Connection Pools → New**
Enter parameters:

1. Pool Name 					: papajavaee-pool
2. Resource Type 				: javax.sql.DataSource
3. Database Driver Vendor 		: Derby

**Additional Properties:**

1. Key(Name) : DatabaseName             ,Value : papajavaee-db
2. Key(Name) : PortNumber               ,Value : 1527
3. Key(Name) : User                     ,Value : APP
4. Key(Name) : Password                 ,Value : APP
5. Key(Name) : serverName               ,Value : localhost
6. Key(Name) : connectionAttributes     ,Value : ;create=true

Please click **Ping** button and see **Ping Succeeded** message after pool is saved.

## Define JDBC Resource

Click **Payara Server Console → Resources → JDBC → JDBC Resources → New**
Enter parameters:
		
1. JNDI Name 	: jdbc/papa-jee18-db
2. Pool Name 	: papajavaee-pool
3. Status 		: Enabled

## Define JNDI for mail server

Click **Payara Server Console → Resources → JNDI → New**

JNDI resource should be defined to enable the email send module with  a “mail/gmail-mail” name and the properties which are written below :

1. JNDI Name 				: mail/gmail-mail
2. Mail Host 				: smtp.gmail.com
3. Default User 			: test@gmail.com
4. Password 				: password
5. Auth 					: Enabled
6. Default Sender Address 	: test@gmail.com
7. Status 					: Enabled


Additional Properties:

1. Name : mail.smtp.port					,Value : 465 
2. Name : mail.smtp.auth					,Value : true
3. Name : mail.smtp.socketFactory.class		,Value : javax.net.ssl.SSLSocketFactory
4. Name : mail.smtp.socketFactory.port		,Value : 465
5. Name : mail.smtp.host					,Value : smtp.gmail.com

*Note: For using another mail server, it is possible to use this JNDI resource with different properties. But, the JNDI name(mail/gmail-mail) should remain the same.*

## Define Security Realm

Firstly, shared **keyfile** file should be put under relative path:

 ~~\payara5\glassfish\domains\domain1\config\

*Ex. : Applications\NetBeans\payara5\glassfish\domains\domain1\config\keyfile*

Then configure the file as fileRealm in Application Server;

Click **Payara Server Console →Configurations →server-config →Security→ Realm→ New**
Enter parameters:

1. Realm Name		:file
2. Class Name		:com.sun.enterprise.security.auth.realm.file.FileRealm
3. JAAS Context		:fileRealm
4. Key File			:${com.sun.aas.instanceRoot}/config/keyfile


---
## SA5 MIN: ARCHITECTURAL/UX DECISIONS

Firstly, entity model was created according to TSSDomainModel. Our team discussed whether we should create a database table for each of the roles or not. To minimize the data duplication and backend code, we decided to define RoleEntity class as an abstract and all roles (EmployeEntity,SecretaryEntity etc.) extended this abstract class. Hence, we could manage defined roles with one table that has a DTYPE column to distinguish roles.

Then, in order to clearly seperate entity class access from layers other than DAO layer, we urged the need of a helper class and determined to define a new package which name is “com.papa.jee18.dto.helper”. These helper classes consist common methods to prevent code repetition, namely handling convert to entity/dto,convert from entity to dto etc.

Apart from that, public holiday access method was argued by our team. We discussed about two options which are web service call and db call. To avoid extra call to another network and not to get error from another resource, we decided to define all public holidays in our database system. As an enhancement request, once-a-year scheduled job could be added in future to feed public holidays for upcoming years.  

Finally, for the ease of usability, we have decided to show the most frequent task according to defined role for user. Hence, we opted to create a single page and to fill content according to roles. i.e. The user who has a supervisor role,for instance, will see the timesheets which are with SIGNED_BY_EMPLOYEE status and the contracts which are with status PREPARED.


