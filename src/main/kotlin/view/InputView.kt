package view

import domain.Lotto
import domain.LottoNumber
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney
import domain.Ticket
import domain.WinningLotto
import view.validator.Validator

class InputView : InputViewInterface {
    override fun askMoney(): PurchaseLottoMoney? {
        val input = readln()
        if (!Validator.validateConvertInt(input)) return null
        val number = input.toInt()
        if (!Validator.validateMakePurchaseLottoMoney(number)) return null
        return PurchaseLottoMoney(number)
    }

    override fun askManualCount(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo? {
        val input = readln()
        if (!Validator.validateConvertInt(input)) return null
        val number = input.toInt()
        if (!Validator.validateMakeLottoPurchaseInfo(purchaseLottoMoney, number)) return null
        return LottoPurchaseInfo(purchaseLottoMoney, number)
    }

    override fun askManualNumbers(purchaseInfo: LottoPurchaseInfo): Ticket {
        val lottos = mutableListOf<Lotto>()
        repeat(purchaseInfo.manualCount) { lottos.add(getManualNumber()) }
        return Ticket(lottos)
    }

    private fun getManualNumber(): Lotto {
        while (true) {
            val input = readln()
            if (!Validator.validateConvertToIntList(input, COMMA)) continue
            val numbers = input.split(COMMA).map { it.trim().toInt() }
            if (!Validator.validateMakeLotto(numbers)) continue
            return Lotto(numbers)
        }
    }

    override fun askWinningNumbers(): Lotto? {
        val input = readln()
        if (!Validator.validateConvertToIntList(input, COMMA)) return null
        val numbers = input.split(COMMA).map { it.trim().toInt() }
        if (!Validator.validateMakeLotto(numbers)) return null
        return Lotto(numbers)
    }

    override fun askBonusNumber(winningNumbers: Lotto): WinningLotto? {
        val input = readln()
        if (!Validator.validateConvertInt(input)) return null
        val number = input.toInt()
        if (!Validator.validateMakeLottoNumber(number)) return null
        val bonusNumber = LottoNumber.from(number)
        if (!Validator.validateMakeWinningLotto(winningNumbers, bonusNumber)) return null
        return WinningLotto(winningNumbers, bonusNumber)
    }

    companion object {
        private const val COMMA = ","
    }
}
