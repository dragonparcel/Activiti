package org.activiti.editor.language.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;

public abstract class AbstractConverterTest {

  protected BpmnModel readXMLFile() throws Exception {
    InputStream xmlStream = this.getClass().getClassLoader().getResourceAsStream(getResource());
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(xmlStream, StandardCharsets.UTF_8);
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    return new BpmnXMLConverter().convertToBpmnModel(xtr);
  }

  protected BpmnModel readXMLFileEncoding(String encoding) throws Exception {
     InputStream xmlStream = this.getClass().getClassLoader().getResourceAsStream(getResource());
     XMLInputFactory xif = XMLInputFactory.newInstance();
     XMLStreamReader xtr = xif.createXMLStreamReader(xmlStream, encoding);
     return new BpmnXMLConverter().convertToBpmnModel(xtr);
  }

  protected BpmnModel exportAndReadXMLFile(BpmnModel bpmnModel) throws Exception {
    byte[] xml = new BpmnXMLConverter().convertToXML(bpmnModel);
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(xml),
        StandardCharsets.UTF_8);
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    return new BpmnXMLConverter().convertToBpmnModel(xtr);
  }

  protected void deployProcess(BpmnModel bpmnModel) {
    /*
     * byte[] xml = new BpmnXMLConverter().convertToXML(bpmnModel); ProcessEngineConfiguration configuration = ProcessEngineConfiguration. createStandaloneInMemProcessEngineConfiguration();
     * ProcessEngine processEngine = configuration.buildProcessEngine(); try { Deployment deployment = processEngine.getRepositoryService().createDeployment().name
     * ("test").addString("test.bpmn20.xml", new String(xml)).deploy(); processEngine .getRepositoryService().deleteDeployment(deployment.getId()); } finally { processEngine.close(); }
     */
  }

  protected abstract String getResource();
}