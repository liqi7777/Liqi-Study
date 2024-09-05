
package com.example.webservice.hangzhouplatlease.company.service.impl;

import com.example.webservice.hangzhouplatlease.core.dao.xsd.SuperDao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import java.lang.Exception;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hzfc.company.service.impl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SaveHtTpDataData_QNAME = new QName("http://impl.service.company.hzfc.com", "data");
    private final static QName _SaveHtTpDataFilename_QNAME = new QName("http://impl.service.company.hzfc.com", "filename");
    private final static QName _SaveHtTpDataJsonstr_QNAME = new QName("http://impl.service.company.hzfc.com", "jsonstr");
    private final static QName _SaveHtTpResponseReturn_QNAME = new QName("http://impl.service.company.hzfc.com", "return");
    private final static QName _GetSavePathStr_QNAME = new QName("http://impl.service.company.hzfc.com", "str");
    private final static QName _CheckStateByFwcodeEncryptToken_QNAME = new QName("http://impl.service.company.hzfc.com", "token");
    private final static QName _RentQyServiceExceptionRentQyServiceException_QNAME = new QName("http://impl.service.company.hzfc.com", "RentQyServiceException");
    private final static QName _GetLkbMessageXqmc_QNAME = new QName("http://impl.service.company.hzfc.com", "xqmc");
    private final static QName _SaveHtDsrlist_QNAME = new QName("http://impl.service.company.hzfc.com", "dsrlist");
    private final static QName _SaveHtInfo_QNAME = new QName("http://impl.service.company.hzfc.com", "info");
    private final static QName _CheckZlryKeywords_QNAME = new QName("http://impl.service.company.hzfc.com", "keywords");
    private final static QName _ExceptionMessage_QNAME = new QName("http://impl.service.company.hzfc.com", "Message");
    private final static QName _SetSuperDaoSuperDao_QNAME = new QName("http://impl.service.company.hzfc.com", "superDao");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hzfc.company.service.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFwStationlistResponse }
     * 
     */
    public GetFwStationlistResponse createGetFwStationlistResponse() {
        return new GetFwStationlistResponse();
    }

    /**
     * Create an instance of {@link UpdateFwZt }
     * 
     */
    public UpdateFwZt createUpdateFwZt() {
        return new UpdateFwZt();
    }

    /**
     * Create an instance of {@link CheckStateByFwcode }
     * 
     */
    public CheckStateByFwcode createCheckStateByFwcode() {
        return new CheckStateByFwcode();
    }

    /**
     * Create an instance of {@link RevokeHtByHtbhResponse }
     * 
     */
    public RevokeHtByHtbhResponse createRevokeHtByHtbhResponse() {
        return new RevokeHtByHtbhResponse();
    }

    /**
     * Create an instance of {@link SaveFwEncrypt }
     * 
     */
    public SaveFwEncrypt createSaveFwEncrypt() {
        return new SaveFwEncrypt();
    }

    /**
     * Create an instance of {@link SaveZlryEncryptResponse }
     * 
     */
    public SaveZlryEncryptResponse createSaveZlryEncryptResponse() {
        return new SaveZlryEncryptResponse();
    }

    /**
     * Create an instance of {@link GetSuperDao }
     * 
     */
    public GetSuperDao createGetSuperDao() {
        return new GetSuperDao();
    }

    /**
     * Create an instance of {@link CheckStateByFwcodeEncrypt }
     * 
     */
    public CheckStateByFwcodeEncrypt createCheckStateByFwcodeEncrypt() {
        return new CheckStateByFwcodeEncrypt();
    }

    /**
     * Create an instance of {@link RevokeHtByHtbhEncryptResponse }
     * 
     */
    public RevokeHtByHtbhEncryptResponse createRevokeHtByHtbhEncryptResponse() {
        return new RevokeHtByHtbhEncryptResponse();
    }

    /**
     * Create an instance of {@link SaveHtTpData }
     * 
     */
    public SaveHtTpData createSaveHtTpData() {
        return new SaveHtTpData();
    }

    /**
     * Create an instance of {@link GetLkbMessage }
     * 
     */
    public GetLkbMessage createGetLkbMessage() {
        return new GetLkbMessage();
    }

    /**
     * Create an instance of {@link SaveFjEncryptResponse }
     * 
     */
    public SaveFjEncryptResponse createSaveFjEncryptResponse() {
        return new SaveFjEncryptResponse();
    }

    /**
     * Create an instance of {@link CheckZlryResponse }
     * 
     */
    public CheckZlryResponse createCheckZlryResponse() {
        return new CheckZlryResponse();
    }

    /**
     * Create an instance of {@link SaveFwTpData }
     * 
     */
    public SaveFwTpData createSaveFwTpData() {
        return new SaveFwTpData();
    }

    /**
     * Create an instance of {@link SaveHtEncryptResponse }
     * 
     */
    public SaveHtEncryptResponse createSaveHtEncryptResponse() {
        return new SaveHtEncryptResponse();
    }

    /**
     * Create an instance of {@link UpdateFwZtResponse }
     * 
     */
    public UpdateFwZtResponse createUpdateFwZtResponse() {
        return new UpdateFwZtResponse();
    }

    /**
     * Create an instance of {@link GetSuperDaoResponse }
     * 
     */
    public GetSuperDaoResponse createGetSuperDaoResponse() {
        return new GetSuperDaoResponse();
    }

    /**
     * Create an instance of {@link SaveHt }
     * 
     */
    public SaveHt createSaveHt() {
        return new SaveHt();
    }

    /**
     * Create an instance of {@link SaveZlryEncrypt }
     * 
     */
    public SaveZlryEncrypt createSaveZlryEncrypt() {
        return new SaveZlryEncrypt();
    }

    /**
     * Create an instance of {@link UpdateFwZtEncrypt }
     * 
     */
    public UpdateFwZtEncrypt createUpdateFwZtEncrypt() {
        return new UpdateFwZtEncrypt();
    }

    /**
     * Create an instance of {@link CheckTokenResponse }
     * 
     */
    public CheckTokenResponse createCheckTokenResponse() {
        return new CheckTokenResponse();
    }

    /**
     * Create an instance of {@link CheckToken }
     * 
     */
    public CheckToken createCheckToken() {
        return new CheckToken();
    }

    /**
     * Create an instance of {@link CheckStateByFwcodeResponse }
     * 
     */
    public CheckStateByFwcodeResponse createCheckStateByFwcodeResponse() {
        return new CheckStateByFwcodeResponse();
    }

    /**
     * Create an instance of {@link GetFwStationlist }
     * 
     */
    public GetFwStationlist createGetFwStationlist() {
        return new GetFwStationlist();
    }

    /**
     * Create an instance of {@link SaveHtPdfIoResponse }
     * 
     */
    public SaveHtPdfIoResponse createSaveHtPdfIoResponse() {
        return new SaveHtPdfIoResponse();
    }

    /**
     * Create an instance of {@link SaveHtTp }
     * 
     */
    public SaveHtTp createSaveHtTp() {
        return new SaveHtTp();
    }

    /**
     * Create an instance of {@link GetLkbMessageResponse }
     * 
     */
    public GetLkbMessageResponse createGetLkbMessageResponse() {
        return new GetLkbMessageResponse();
    }

    /**
     * Create an instance of {@link SaveFwTpResponse }
     * 
     */
    public SaveFwTpResponse createSaveFwTpResponse() {
        return new SaveFwTpResponse();
    }

    /**
     * Create an instance of {@link RevokeHtByHtbhEncrypt }
     * 
     */
    public RevokeHtByHtbhEncrypt createRevokeHtByHtbhEncrypt() {
        return new RevokeHtByHtbhEncrypt();
    }

    /**
     * Create an instance of {@link SaveFwTp }
     * 
     */
    public SaveFwTp createSaveFwTp() {
        return new SaveFwTp();
    }

    /**
     * Create an instance of {@link SetSuperDao }
     * 
     */
    public SetSuperDao createSetSuperDao() {
        return new SetSuperDao();
    }

    /**
     * Create an instance of {@link SaveZlryResponse }
     * 
     */
    public SaveZlryResponse createSaveZlryResponse() {
        return new SaveZlryResponse();
    }

    /**
     * Create an instance of {@link SaveHtResponse }
     * 
     */
    public SaveHtResponse createSaveHtResponse() {
        return new SaveHtResponse();
    }

    /**
     * Create an instance of {@link SaveFwResponse }
     * 
     */
    public SaveFwResponse createSaveFwResponse() {
        return new SaveFwResponse();
    }

    /**
     * Create an instance of {@link GetSavePathResponse }
     * 
     */
    public GetSavePathResponse createGetSavePathResponse() {
        return new GetSavePathResponse();
    }

    /**
     * Create an instance of {@link RevokeHtByHtbh }
     * 
     */
    public RevokeHtByHtbh createRevokeHtByHtbh() {
        return new RevokeHtByHtbh();
    }

    /**
     * Create an instance of {@link SaveFwEncryptResponse }
     * 
     */
    public SaveFwEncryptResponse createSaveFwEncryptResponse() {
        return new SaveFwEncryptResponse();
    }

    /**
     * Create an instance of {@link SaveHtTpDataResponse }
     * 
     */
    public SaveHtTpDataResponse createSaveHtTpDataResponse() {
        return new SaveHtTpDataResponse();
    }

    /**
     * Create an instance of {@link UpdateFwZtEncryptResponse }
     * 
     */
    public UpdateFwZtEncryptResponse createUpdateFwZtEncryptResponse() {
        return new UpdateFwZtEncryptResponse();
    }

    /**
     * Create an instance of {@link SaveFjResponse }
     * 
     */
    public SaveFjResponse createSaveFjResponse() {
        return new SaveFjResponse();
    }

    /**
     * Create an instance of {@link SaveFw }
     * 
     */
    public SaveFw createSaveFw() {
        return new SaveFw();
    }

    /**
     * Create an instance of {@link CheckZlry }
     * 
     */
    public CheckZlry createCheckZlry() {
        return new CheckZlry();
    }

    /**
     * Create an instance of {@link SaveFwTpDataResponse }
     * 
     */
    public SaveFwTpDataResponse createSaveFwTpDataResponse() {
        return new SaveFwTpDataResponse();
    }

    /**
     * Create an instance of {@link CheckStateByFwcodeEncryptResponse }
     * 
     */
    public CheckStateByFwcodeEncryptResponse createCheckStateByFwcodeEncryptResponse() {
        return new CheckStateByFwcodeEncryptResponse();
    }

    /**
     * Create an instance of {@link SaveFj }
     * 
     */
    public SaveFj createSaveFj() {
        return new SaveFj();
    }

    /**
     * Create an instance of {@link SaveFjEncrypt }
     * 
     */
    public SaveFjEncrypt createSaveFjEncrypt() {
        return new SaveFjEncrypt();
    }

    /**
     * Create an instance of {@link SaveZlry }
     * 
     */
    public SaveZlry createSaveZlry() {
        return new SaveZlry();
    }

    /**
     * Create an instance of {@link SaveHtTpResponse }
     * 
     */
    public SaveHtTpResponse createSaveHtTpResponse() {
        return new SaveHtTpResponse();
    }

    /**
     * Create an instance of {@link SaveHtPdfIo }
     * 
     */
    public SaveHtPdfIo createSaveHtPdfIo() {
        return new SaveHtPdfIo();
    }

    /**
     * Create an instance of {@link RentQyServiceException }
     * 
     */
    public RentQyServiceException createRentQyServiceException() {
        return new RentQyServiceException();
    }

    /**
     * Create an instance of {@link java.lang.Exception }
     * 
     */
    public java.lang.Exception createException() {
        return new java.lang.Exception();
    }

    /**
     * Create an instance of {@link GetSavePath }
     * 
     */
    public GetSavePath createGetSavePath() {
        return new GetSavePath();
    }

    /**
     * Create an instance of {@link SaveHtEncrypt }
     * 
     */
    public SaveHtEncrypt createSaveHtEncrypt() {
        return new SaveHtEncrypt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "data", scope = SaveHtTpData.class)
    public JAXBElement<byte[]> createSaveHtTpDataData(byte[] value) {
        return new JAXBElement<byte[]>(_SaveHtTpDataData_QNAME, byte[].class, SaveHtTpData.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "filename", scope = SaveHtTpData.class)
    public JAXBElement<String> createSaveHtTpDataFilename(String value) {
        return new JAXBElement<String>(_SaveHtTpDataFilename_QNAME, String.class, SaveHtTpData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveHtTpData.class)
    public JAXBElement<String> createSaveHtTpDataJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveHtTpData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveHtTpResponse.class)
    public JAXBElement<String> createSaveHtTpResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveHtTpResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "str", scope = GetSavePath.class)
    public JAXBElement<String> createGetSavePathStr(String value) {
        return new JAXBElement<String>(_GetSavePathStr_QNAME, String.class, GetSavePath.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveZlryEncryptResponse.class)
    public JAXBElement<String> createSaveZlryEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveZlryEncryptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = CheckStateByFwcodeEncrypt.class)
    public JAXBElement<String> createCheckStateByFwcodeEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, CheckStateByFwcodeEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = CheckStateByFwcodeEncrypt.class)
    public JAXBElement<String> createCheckStateByFwcodeEncryptJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, CheckStateByFwcodeEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = RevokeHtByHtbhEncryptResponse.class)
    public JAXBElement<String> createRevokeHtByHtbhEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, RevokeHtByHtbhEncryptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveHtResponse.class)
    public JAXBElement<String> createSaveHtResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveHtResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = SaveFwEncrypt.class)
    public JAXBElement<String> createSaveFwEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, SaveFwEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveFwEncrypt.class)
    public JAXBElement<String> createSaveFwEncryptJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveFwEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = UpdateFwZt.class)
    public JAXBElement<String> createUpdateFwZtJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, UpdateFwZt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveHtPdfIo.class)
    public JAXBElement<String> createSaveHtPdfIoJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveHtPdfIo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveFw.class)
    public JAXBElement<String> createSaveFwJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveFw.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveFwTp.class)
    public JAXBElement<String> createSaveFwTpJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveFwTp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = GetFwStationlist.class)
    public JAXBElement<String> createGetFwStationlistToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, GetFwStationlist.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveHtTpDataResponse.class)
    public JAXBElement<String> createSaveHtTpDataResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveHtTpDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveHtPdfIoResponse.class)
    public JAXBElement<String> createSaveHtPdfIoResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveHtPdfIoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = GetFwStationlistResponse.class)
    public JAXBElement<String> createGetFwStationlistResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, GetFwStationlistResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link java.lang.Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "RentQyServiceException", scope = RentQyServiceException.class)
    public JAXBElement<java.lang.Exception> createRentQyServiceExceptionRentQyServiceException(java.lang.Exception value) {
        return new JAXBElement<java.lang.Exception>(_RentQyServiceExceptionRentQyServiceException_QNAME, java.lang.Exception.class, RentQyServiceException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveFwResponse.class)
    public JAXBElement<String> createSaveFwResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveFwResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveFjEncryptResponse.class)
    public JAXBElement<String> createSaveFjEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveFjEncryptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = SaveZlryEncrypt.class)
    public JAXBElement<String> createSaveZlryEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, SaveZlryEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveZlryEncrypt.class)
    public JAXBElement<String> createSaveZlryEncryptJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveZlryEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveFwEncryptResponse.class)
    public JAXBElement<String> createSaveFwEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveFwEncryptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "xqmc", scope = GetLkbMessage.class)
    public JAXBElement<String> createGetLkbMessageXqmc(String value) {
        return new JAXBElement<String>(_GetLkbMessageXqmc_QNAME, String.class, GetLkbMessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = GetLkbMessage.class)
    public JAXBElement<String> createGetLkbMessageToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, GetLkbMessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveZlryResponse.class)
    public JAXBElement<String> createSaveZlryResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveZlryResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveFwTpDataResponse.class)
    public JAXBElement<String> createSaveFwTpDataResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveFwTpDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveFj.class)
    public JAXBElement<String> createSaveFjJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveFj.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "dsrlist", scope = SaveHt.class)
    public JAXBElement<String> createSaveHtDsrlist(String value) {
        return new JAXBElement<String>(_SaveHtDsrlist_QNAME, String.class, SaveHt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = SaveHt.class)
    public JAXBElement<String> createSaveHtToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, SaveHt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "info", scope = SaveHt.class)
    public JAXBElement<String> createSaveHtInfo(String value) {
        return new JAXBElement<String>(_SaveHtInfo_QNAME, String.class, SaveHt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = GetSavePathResponse.class)
    public JAXBElement<String> createGetSavePathResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, GetSavePathResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = CheckStateByFwcodeResponse.class)
    public JAXBElement<String> createCheckStateByFwcodeResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, CheckStateByFwcodeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveZlry.class)
    public JAXBElement<String> createSaveZlryJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveZlry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuperDao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = GetSuperDaoResponse.class)
    public JAXBElement<SuperDao> createGetSuperDaoResponseReturn(SuperDao value) {
        return new JAXBElement<SuperDao>(_SaveHtTpResponseReturn_QNAME, SuperDao.class, GetSuperDaoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = CheckTokenResponse.class)
    public JAXBElement<String> createCheckTokenResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, CheckTokenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "data", scope = SaveFwTpData.class)
    public JAXBElement<byte[]> createSaveFwTpDataData(byte[] value) {
        return new JAXBElement<byte[]>(_SaveHtTpDataData_QNAME, byte[].class, SaveFwTpData.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "filename", scope = SaveFwTpData.class)
    public JAXBElement<String> createSaveFwTpDataFilename(String value) {
        return new JAXBElement<String>(_SaveHtTpDataFilename_QNAME, String.class, SaveFwTpData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveFwTpData.class)
    public JAXBElement<String> createSaveFwTpDataJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveFwTpData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = RevokeHtByHtbh.class)
    public JAXBElement<String> createRevokeHtByHtbhJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, RevokeHtByHtbh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = RevokeHtByHtbhEncrypt.class)
    public JAXBElement<String> createRevokeHtByHtbhEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, RevokeHtByHtbhEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = RevokeHtByHtbhEncrypt.class)
    public JAXBElement<String> createRevokeHtByHtbhEncryptJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, RevokeHtByHtbhEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = UpdateFwZtEncrypt.class)
    public JAXBElement<String> createUpdateFwZtEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, UpdateFwZtEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = UpdateFwZtEncrypt.class)
    public JAXBElement<String> createUpdateFwZtEncryptJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, UpdateFwZtEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = CheckStateByFwcode.class)
    public JAXBElement<String> createCheckStateByFwcodeJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, CheckStateByFwcode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = CheckZlryResponse.class)
    public JAXBElement<String> createCheckZlryResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, CheckZlryResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = UpdateFwZtResponse.class)
    public JAXBElement<String> createUpdateFwZtResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, UpdateFwZtResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveHtEncryptResponse.class)
    public JAXBElement<String> createSaveHtEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveHtEncryptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveFwTpResponse.class)
    public JAXBElement<String> createSaveFwTpResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveFwTpResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "keywords", scope = CheckZlry.class)
    public JAXBElement<String> createCheckZlryKeywords(String value) {
        return new JAXBElement<String>(_CheckZlryKeywords_QNAME, String.class, CheckZlry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = CheckZlry.class)
    public JAXBElement<String> createCheckZlryToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, CheckZlry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = CheckToken.class)
    public JAXBElement<String> createCheckTokenToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, CheckToken.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = SaveFjEncrypt.class)
    public JAXBElement<String> createSaveFjEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, SaveFjEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveFjEncrypt.class)
    public JAXBElement<String> createSaveFjEncryptJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveFjEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = GetLkbMessageResponse.class)
    public JAXBElement<String> createGetLkbMessageResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, GetLkbMessageResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "dsrlist", scope = SaveHtEncrypt.class)
    public JAXBElement<String> createSaveHtEncryptDsrlist(String value) {
        return new JAXBElement<String>(_SaveHtDsrlist_QNAME, String.class, SaveHtEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "token", scope = SaveHtEncrypt.class)
    public JAXBElement<String> createSaveHtEncryptToken(String value) {
        return new JAXBElement<String>(_CheckStateByFwcodeEncryptToken_QNAME, String.class, SaveHtEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "info", scope = SaveHtEncrypt.class)
    public JAXBElement<String> createSaveHtEncryptInfo(String value) {
        return new JAXBElement<String>(_SaveHtInfo_QNAME, String.class, SaveHtEncrypt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = SaveFjResponse.class)
    public JAXBElement<String> createSaveFjResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, SaveFjResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "jsonstr", scope = SaveHtTp.class)
    public JAXBElement<String> createSaveHtTpJsonstr(String value) {
        return new JAXBElement<String>(_SaveHtTpDataJsonstr_QNAME, String.class, SaveHtTp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "Message", scope = java.lang.Exception.class)
    public JAXBElement<String> createExceptionMessage(String value) {
        return new JAXBElement<String>(_ExceptionMessage_QNAME, String.class, Exception.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = RevokeHtByHtbhResponse.class)
    public JAXBElement<String> createRevokeHtByHtbhResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, RevokeHtByHtbhResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuperDao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "superDao", scope = SetSuperDao.class)
    public JAXBElement<SuperDao> createSetSuperDaoSuperDao(SuperDao value) {
        return new JAXBElement<SuperDao>(_SetSuperDaoSuperDao_QNAME, SuperDao.class, SetSuperDao.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = UpdateFwZtEncryptResponse.class)
    public JAXBElement<String> createUpdateFwZtEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, UpdateFwZtEncryptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.company.hzfc.com", name = "return", scope = CheckStateByFwcodeEncryptResponse.class)
    public JAXBElement<String> createCheckStateByFwcodeEncryptResponseReturn(String value) {
        return new JAXBElement<String>(_SaveHtTpResponseReturn_QNAME, String.class, CheckStateByFwcodeEncryptResponse.class, value);
    }

}
