/*
 * matrix-appservice-email - Matrix Bridge to E-mail
 * Copyright (C) 2017 Kamax Sarl
 *
 * https://www.kamax.io/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.kamax.matrix.bridge.email.controller;

import io.kamax.matrix.MatrixID;
import io.kamax.matrix.bridge.email.model.subscription._BridgeSubscription;
import io.kamax.matrix.client._MatrixClient;
import io.kamax.matrix.hs._MatrixRoom;

import java.time.Instant;

public class SubscriptionItem {

    private String id;
    private String token;
    private String name;
    private Instant date;
    private String creator;

    public SubscriptionItem(_BridgeSubscription sub) {
        _MatrixClient client = sub.getMatrixEndpoint().getClient();
        _MatrixRoom room = client.getRoom(sub.getMatrixEndpoint().getChannelId());

        this.id = sub.getId();
        this.token = sub.getEmailKey();
        this.name = room.getName().orElse(room.getAddress());
        this.creator = client.getUser(new MatrixID(sub.getInitiator())).getName().orElse(sub.getInitiator());
        this.date = sub.getCreation();
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public Instant getDate() {
        return date;
    }

    public String getCreator() {
        return creator;
    }

}
