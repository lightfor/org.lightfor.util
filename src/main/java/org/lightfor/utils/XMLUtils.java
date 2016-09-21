package org.lightfor.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * XML工具类
 * Created by Light on 2016/1/29.
 */
public class XMLUtils {
    /**
     * DocumentBuilderFactory实例
     */
    private static DocumentBuilderFactory documentBuilderFactory = createDocumentBuilderFactory();

    /**
     * 获取DocumentBuilderFactory
     * @return 实例
     */
    private static DocumentBuilderFactory createDocumentBuilderFactory() {
        return DocumentBuilderFactory.newInstance();
    }

    /**
     * 由DocumentBuilderFactory产生DocumentBuilder实例
     * @return DocumentBuilder
     */
    public static DocumentBuilder createDocumentBuilder() {
        try {
            return documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            return null;
        }
    }

    /**
     * 从element元素查找所有tagName指定的子节点元素集合
     * @param element 元素
     * @param tagName tag
     * @return 符合元素集
     */
    public static List<Element> elements(Element element, String tagName) {
        if (element == null || !element.hasChildNodes()) {
            return Collections.emptyList();
        }

        List<Element> elements = new ArrayList<>();
        for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) child;
                String childTagName = childElement.getNodeName();
                if (tagName.equals(childTagName))
                    elements.add(childElement);
            }
        }
        return elements;
    }
}
