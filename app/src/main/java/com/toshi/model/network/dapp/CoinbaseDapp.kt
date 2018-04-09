/*
 * 	Copyright (c) 2017. Toshi Inc
 *
 *  This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.toshi.model.network.dapp

import com.toshi.manager.dappInjection.DappsInjector.Companion.MARKETPLACE_ID

data class CoinbaseDapp(
        override val dappId: Long = 1L,
        override val name: String = "Coinbase",
        override val url: String = "coinbase.com",
        override val description: String = "Coinbase description",
        override val icon: String = "https://www.google.no/search?q=coinbase&source=lnms&tbm=isch&sa=X&ved=0ahUKEwit7POL2KzaAhWko1kKHZ-KDqwQ_AUICygC&biw=1280&bih=1342&dpr=1#imgrc=3PYuQeAgJXA25M:",
        override val cover: String = "https://www.google.no/search?q=coinbase&source=lnms&tbm=isch&sa=X&ved=0ahUKEwit7POL2KzaAhWko1kKHZ-KDqwQ_AUICygC&biw=1280&bih=1342&dpr=1#imgrc=MZaq-qNPB1I-cM:",
        override val categories: ArrayList<Int> = arrayListOf(MARKETPLACE_ID)
) : Dapp(
        dappId = dappId,
        name = name,
        url = url,
        description = description,
        icon = icon,
        cover = cover,
        categories = categories
)