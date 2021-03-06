/*
 *
 *  * 	Copyright (c) 2018. Toshi Inc
 *  *
 *  * 	This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.toshi

import android.content.SharedPreferences
import com.toshi.crypto.HDWallet
import com.toshi.model.local.network.Network
import com.toshi.model.local.network.Networks
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

const val masterSeed = "abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon about"
val network = Network("116", "Toshi Internal Test Network", "https://ethereum.internal.service.toshi.org")

fun mockNetwork(network: Network): Networks {
    val networks = Mockito.mock(Networks::class.java)
    Mockito
            .`when`(networks.onDefaultNetwork())
            .thenReturn(true)
    Mockito
            .`when`(networks.currentNetworkId)
            .thenReturn(network.id)
    Mockito
            .`when`(networks.currentNetwork)
            .thenReturn(network)

    return networks
}

fun mockWallet(masterSeed: String): HDWallet {
    val sharedPreferencesMock = Mockito.mock(SharedPreferences::class.java)
    Mockito
            .`when`(sharedPreferencesMock.getString(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
            .thenReturn(masterSeed)
    return HDWallet(sharedPreferencesMock)
            .getExistingWallet()
            .toBlocking()
            .value()
}