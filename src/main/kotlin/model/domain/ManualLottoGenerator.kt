package model.domain

import model.LottoNumber

class ManualLottoGenerator : LottoGenerator {
    private val manualLottoBundle: MutableList<List<LottoNumber>> = mutableListOf()

    override fun generate(): List<LottoNumber> {
        return manualLottoBundle.removeFirst()
    }

    fun makeBundleOfManualLotto(lotto: List<LottoNumber>) = manualLottoBundle.add(lotto)
}
