package view

import domain.Lotto
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney
import domain.Ticket
import domain.WinningLotto
import view.validator.Validator

class InputView : InputViewInterface {
    override fun askMoney(): PurchaseLottoMoney? {
        val number = Validator.validateConvertInt(readln()) ?: return null
        return Validator.validateMakePurchaseLottoMoney(number)
    }

    override fun askManualCount(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo? {
        val number = Validator.validateConvertInt(readln()) ?: return null
        return Validator.validateMakeLottoPurchaseInfo(purchaseLottoMoney, number)
    }

    override fun askManualNumbers(purchaseInfo: LottoPurchaseInfo): Ticket {
        val lottos = mutableListOf<Lotto>()
        repeat(purchaseInfo.manualCount) { lottos.add(getManualNumber()) }
        return Ticket(lottos)
    }

    private fun getManualNumber(): Lotto {
        while (true) {
            val numbers = Validator.validateConvertToIntList(readln(), COMMA) ?: continue
            return Validator.validateMakeLotto(numbers) ?: continue
        }
    }

    override fun askWinningNumbers(): Lotto? {
        val numbers = Validator.validateConvertToIntList(readln(), COMMA) ?: return null
        return Validator.validateMakeLotto(numbers)
    }

    override fun askBonusNumber(winningNumbers: Lotto): WinningLotto? {
        val number = Validator.validateConvertInt(readln()) ?: return null
        val bonusNumber = Validator.validateMakeLottoNumber(number) ?: return null
        return Validator.validateMakeWinningLotto(winningNumbers, bonusNumber)
    }

    companion object {
        private const val COMMA = ","
    }
}
