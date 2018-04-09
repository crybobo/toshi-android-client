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

package com.toshi.manager.dappInjection

import com.toshi.model.network.dapp.CoinbaseDapp
import com.toshi.model.network.dapp.Dapp
import com.toshi.model.network.dapp.DappSearchResult
import com.toshi.model.network.dapp.DappSection
import com.toshi.model.network.dapp.DappSections

class DappsInjector {

    companion object {
        const val MARKETPLACE_ID = 2
    }

    fun addCoinbaseDappToMarketplaceSection(dappSections: DappSections): DappSections {
        val marketPlaceSection = findMarketplaceSection(dappSections)
        if (marketPlaceSection == null) {
            val dappSection = DappSection(MARKETPLACE_ID, mutableListOf(CoinbaseDapp()))
            dappSections.sections.add(dappSection)
        } else marketPlaceSection.dapps.add(CoinbaseDapp())
        return dappSections
    }

    private fun findMarketplaceSection(dappSections: DappSections): DappSection? {
        return try {
            dappSections.sections.first { it.categoryId == MARKETPLACE_ID }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    fun addCoinbaseDappToSearchResult(searchResult: DappSearchResult, currentDapps: List<Dapp>): DappSearchResult {
        return if (currentDapps.contains(CoinbaseDapp())) return searchResult
        else {
            searchResult.results.dapps.add(CoinbaseDapp())
            searchResult
        }
    }

    fun getDappsOffset(currentDapps: List<Dapp>): Int {
        return if (currentDapps.contains(CoinbaseDapp())) currentDapps.size - 1
        else currentDapps.size
    }
}