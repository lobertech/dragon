package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.contract.fingerprint.DeviceDefinition;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class DeviceDefinitionParser extends BaseQObjectParser {

    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof DeviceDefinition;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            DeviceDefinition deviceDefinition = (DeviceDefinition) fingerprint;

            UUID conversationID = deviceDefinition.getConversationID();

            String brand = deviceDefinition.getBrand();
            String model = deviceDefinition.getModel();
            Integer api = deviceDefinition.getApi();
            String abi = deviceDefinition.getAbi();
            String kernelVersion = deviceDefinition.getKernelVersion();
            Instant kernelBuildAt = deviceDefinition.getKernelBuildAt();
            List<String> ips = deviceDefinition.getIps();
            String aid = deviceDefinition.getAid();
            String tag = deviceDefinition.getTag();

            StringJoiner ipsJsonArray = new StringJoiner(",", "[", "]");
            for (String ip : ips) {
                ipsJsonArray.add("\"" + ip + "\"");
            }

            StringJoiner deviceDefinitionJsonString = new StringJoiner(",", "{", "}");
            deviceDefinitionJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            deviceDefinitionJsonString.add("\"brand\":\"" + brand + "\"");
            deviceDefinitionJsonString.add("\"model\":\"" + model + "\"");
            deviceDefinitionJsonString.add("\"api\":" + api.toString());
            deviceDefinitionJsonString.add("\"abi\":\"" + abi + "\"");
            deviceDefinitionJsonString.add("\"kernelVersion\":\"" + kernelVersion + "\"");
            deviceDefinitionJsonString.add("\"kernelBuildAt\":\"" + kernelBuildAt + "\"");
            deviceDefinitionJsonString.add("\"ips\":" + ipsJsonArray);
            deviceDefinitionJsonString.add("\"aid\":\"" + aid + "\"");
            deviceDefinitionJsonString.add("\"tag\":\"" + tag + "\"");

            return super.PrefixClassName(fingerprint, deviceDefinitionJsonString.toString());
        }
        return null;
    }

    /**
     * 获取解析对象的类型名称
     *
     * @return
     */
    @Override
    public String getObjectClassName() {
        return DeviceDefinition.class.getName();
    }

}
