package io.github.hylexus.jt.utils;

import io.github.hylexus.jt.builder.jt808.Jt808MsgBuilder;
import io.github.hylexus.jt.config.JtProtocolConstant;
import io.github.hylexus.jt.data.msg.BuiltinJt808MsgType;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;

public class MsgBuilderTest {

    private Jt808MsgBuilder builder;
    private static final Charset CHARSET = JtProtocolConstant.JT_808_STRING_ENCODING;

    @Before
    public void setUp() {
        builder = Jt808MsgBuilder.newBuilder();
    }

    @Test
    public void testBuildRegisterMsg() {

        this.builder
                .header(jt808MsgHeaderBuilder -> jt808MsgHeaderBuilder
                        .withMsgId(BuiltinJt808MsgType.CLIENT_REGISTER)
                        .withTerminalId("13166048888")
                        .withFlowId(3402)
                        .withEncryptionType(0b000)
                )
                .body(messageBuilder -> messageBuilder
                        .appendWord(11) //省域 ID [WORD]
                        .appendWord(0)// 市县域 ID [WORD]
                        .appendBytes("12359".getBytes(CHARSET)) // 制造商 ID [BYTE[5]]
                        .appendBytes("12345678901234567890".getBytes(CHARSET)) // 终端型号 [BYTE[20]]
                        .appendBytes("id12345".getBytes(CHARSET)) // 终端 ID [BYTE[7]]
                        .appendByte(1) // 车牌颜色 [BYTE]
                        .appendString("13160466666")
                ).build();

        System.out.println(HexStringUtils.bytes2HexString(builder.build(true)));
        System.out.println(HexStringUtils.bytes2HexString(builder.build(false)));
    }
}