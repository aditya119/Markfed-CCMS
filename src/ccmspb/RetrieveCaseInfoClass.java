/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.io.File;

/**
 *
 * @author HP
 */
public class RetrieveCaseInfoClass {
    File petitionerFile=null, respondentFile=null, orderFile = null;
    int proceedingNumber;
    String fileNumberField,courtList, caseTypeList, locationList,yearList,caseNumber,datePickerCaseFiledOn, datePickerNoticeReceivedOn, datePickerFirstHearingField ,datePickerNextHearingField,
            petitionerName,petitionerEmail,petitionerAddress,petitionerFileName,petitionerFileType,respondentFileName,respondentFileType,respondentName,respondentEmail,respondentAddress,description=null,datePickerProceeding,lawyer,decision,orderFileName;
}