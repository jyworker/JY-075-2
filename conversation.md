# 构建 EMV CPM 二维码生成模块

> **Workspace:** /home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2

---

**User**

从零构建 EMV CPM 消费者二维码生成模块，支持添加  PayloadFormatIndicator 、 ApplicationTemplate  列表及  CommonDataTemplate ，将完整结构序列化为 BER-TLV 字节数组，并对外提供  toBase64()  和  toHex()  两种输出方法，满足移动支付场景需求。

**Assistant**

我来帮你构建 EMV CPM 消费者二维码生成模块。首先让我了解一下项目结构和技术栈。

这是一个 Java EMV QR Code 项目，已经有解码器和模型。我需要理解现有结构，然后构建编码器（生成模块）。让我先读取关键文件。


让我继续读取更多关键文件来理解完整的结构。


让我继续查看测试类和其他相关文件，以及检查是否有 encoder 相关的代码。




让我查看 pom.xml 并运行现有测试来验证当前状态。






现在我已经完全理解了项目结构。让我先运行现有测试，然后创建任务列表。



测试通过。现在让我修复模板类中的 BER-TLV 长度编码 bug。`out.write(len)` 直接写入 int 的低 8 位，当长度 > 127 时会出错。应该使用 `BERUtils.lengthOfValue()`。




现在修复 4 个模板类中的长度编码 bug。需要在每个文件中添加 BERUtils import 并将 `out.write(len)` 替换为 `out.write(BERUtils.lengthOfValue(len))`。




现在运行测试验证长度编码修复没有破坏现有功能。





所有测试通过！现在创建 encoder/cpm 包和构建器类，提供流式 API 来构建 CPM 二维码。






现在让我创建 encoder 包结构。首先创建 `ConsumerPresentedModeBuilder` 主构建器，以及各个子模板的构建器。







现在编译并运行所有测试来验证修复和新功能。







编译成功！现在运行所有 CPM 相关测试。







CPM model 测试全部通过。让我再运行整个测试套件以及验证器和编码器测试。








所有测试都通过了。让我运行完整测试套件并检查诊断信息。






全部测试通过！让我检查诊断信息并完成任务。








已完成 EMV CPM 消费者二维码生成模块的构建。以下是本次工作的总结：

## 一、修复 BER-TLV 长度编码 Bug

在 4 个模板类中发现并修复了严重的长度编码问题：原代码使用 `out.write(len)` 直接写入 int 的低 8 位，当模板内容超过 127 字节时会产生错误的 BER-TLV 编码。修复后统一使用 `BERUtils.lengthOfValue(len)` 进行规范编码。

涉及文件：
- [ApplicationTemplate.java](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/model/cpm/ApplicationTemplate.java#L83-L88)
- [CommonDataTemplate.java](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/model/cpm/CommonDataTemplate.java#L83-L88)
- [ApplicationSpecificTransparentTemplate.java](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/model/cpm/ApplicationSpecificTransparentTemplate.java#L64-L66)
- [CommonDataTransparentTemplate.java](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/model/cpm/CommonDataTransparentTemplate.java#L64-L66)

## 二、新增 encoder/cpm 包 - 流式构建器 API

创建了 5 个构建器类，提供简洁易用的流式 API：

| 构建器类 | 用途 |
|---------|------|
| [ConsumerPresentedModeBuilder](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/encoder/cpm/ConsumerPresentedModeBuilder.java) | 主构建器，支持 PayloadFormatIndicator、ApplicationTemplate 列表、CommonDataTemplate |
| [ApplicationTemplateBuilder](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/encoder/cpm/ApplicationTemplateBuilder.java) | 构建应用模板，支持所有 EMV 标签字段 |
| [CommonDataTemplateBuilder](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/encoder/cpm/CommonDataTemplateBuilder.java) | 构建通用数据模板 |
| [ApplicationSpecificTransparentTemplateBuilder](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/encoder/cpm/ApplicationSpecificTransparentTemplateBuilder.java) | 构建应用透明模板 |
| [CommonDataTransparentTemplateBuilder](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/encoder/cpm/CommonDataTransparentTemplateBuilder.java) | 构建通用透明模板 |

## 三、序列化与输出

`ConsumerPresentedMode` 已支持完整的 BER-TLV 字节数组序列化，并提供两种输出方法：
- `toBase64()` - Base64 编码输出（见 [ConsumerPresentedMode.java#L122-L124](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/model/cpm/ConsumerPresentedMode.java#L122-L124)）
- `toHex()` - 十六进制字符串输出（见 [ConsumerPresentedMode.java#L132-L134](file:///home/jiangyong/ws/bytedance/solo0601/JY-075/JY-075-2/src/main/java/com/emv/qrcode/model/cpm/ConsumerPresentedMode.java#L132-L134)）

## 四、使用示例

```java
// 使用流式构建器生成 CPM 二维码
String hex = ConsumerPresentedModeBuilder.newBuilder()
    .withPayloadFormatIndicator()
    .addApplicationTemplate(t -> t
        .withApplicationDefinitionFileName("A0000000555555")
        .withApplicationLabel("Product1"))
    .addApplicationTemplate(t -> t
        .withApplicationDefinitionFileName("A0000000666666")
        .withApplicationLabel("Product2"))
    .withCommonDataTemplate(t -> t
        .withApplicationPAN("1234567890123458")
        .withCardholderName("CARDHOLDER/EMV")
        .withLanguagePreference("ruesdeen")
        .withCommonDataTransparentTemplate(tt -> tt
            .withIssuerApplicationData("06010A03000000")
            .withApplicationCryptogram("584FD385FA234BCC")
            .withApplicationTransactionCounter("0001")
            .withUnpredictableNumber("6D58EF13")))
    .toHex();

String base64 = ConsumerPresentedModeBuilder.newBuilder()
    .withPayloadFormatIndicator()
    // ... 同上配置
    .toBase64();
```

所有 100+ 项单元测试均通过，无编译错误或诊断警告。

---

