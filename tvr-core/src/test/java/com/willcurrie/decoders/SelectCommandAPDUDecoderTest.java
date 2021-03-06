package com.willcurrie.decoders;

import com.willcurrie.DecodedData;
import com.willcurrie.decoders.apdu.SelectCommandAPDUDecoder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SelectCommandAPDUDecoderTest {

    @Test
    public void testDecodeSelectByAID() throws Exception {
        String command = "00A4040007A000000004101000";
        String reply = "6F1C8407A0000000041010A511500F505043204D434420303420207632309000";
        String input = command + reply;
        DecodedData decodedData = new SelectCommandAPDUDecoder().decode(input, 0, new DecodeSession());
        assertThat(decodedData.getRawData(), is("C-APDU: Select"));
        assertThat(decodedData.getDecodedData(), is("AID A0000000041010"));
        assertThat(decodedData.getEndIndex(), is(command.length()/2));
    }

    @Test
    public void testDecodeSelectByFilename() throws Exception {
        String command = "00A404000E315041592E5359532E444446303100";
        String reply = "6F1A840E315041592E5359532E4444463031A5088801015F2D02656E9000";
        String input = command + reply;
        DecodedData decodedData = new SelectCommandAPDUDecoder().decode(input, 0, new DecodeSession());
        assertThat(decodedData.getRawData(), is("C-APDU: Select"));
        assertThat(decodedData.getDecodedData(), is("1PAY.SYS.DDF01"));
        assertThat(decodedData.getEndIndex(), is(command.length()/2));
    }
}
