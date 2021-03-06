/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.rfxcom.internal.messages;

import static org.junit.Assert.assertEquals;

import org.eclipse.smarthome.core.util.HexUtils;
import org.junit.Test;
import org.openhab.binding.rfxcom.internal.exceptions.RFXComException;

/**
 * Test for RFXCom-binding
 *
 * @author Martin van Wingerden
 * @author Mike Jagdis
 */
public class RFXComUVMessageTest {
    @Test
    public void testMessage1() throws RFXComException {
        String hexMessage = "095703123421194731E9";

        byte[] message = HexUtils.hexToBytes(hexMessage);
        RFXComUVMessage msg = (RFXComUVMessage) RFXComMessageFactory.createMessage(message);

        assertEquals("SubType", RFXComUVMessage.SubType.UV3, msg.subType);
        assertEquals("Seq Number", 18, msg.seqNbr);
        assertEquals("Sensor Id", "13345", msg.getDeviceId());

        assertEquals("UV", 2.5, msg.uv, 0.001);
        assertEquals("Temperature", 1822.5, msg.temperature, 0.001);

        assertEquals("Signal Level", 14, msg.signalLevel);
        assertEquals("Battery Level", 9, msg.batteryLevel);

        byte[] decoded = msg.decodeMessage();

        assertEquals("Message converted back", hexMessage, HexUtils.bytesToHex(decoded));
    }

    @Test
    public void testMessage2() throws RFXComException {
        String hexMessage = "09570312342119C731E9";

        byte[] message = HexUtils.hexToBytes(hexMessage);
        RFXComUVMessage msg = (RFXComUVMessage) RFXComMessageFactory.createMessage(message);

        assertEquals("SubType", RFXComUVMessage.SubType.UV3, msg.subType);
        assertEquals("Seq Number", 18, msg.seqNbr);
        assertEquals("Sensor Id", "13345", msg.getDeviceId());

        assertEquals("UV", 2.5, msg.uv, 0.001);
        assertEquals("Temperature", -1822.5, msg.temperature, 0.001);

        assertEquals("Signal Level", 14, msg.signalLevel);
        assertEquals("Battery Level", 9, msg.batteryLevel);

        byte[] decoded = msg.decodeMessage();

        assertEquals("Message converted back", hexMessage, HexUtils.bytesToHex(decoded));
    }
}
