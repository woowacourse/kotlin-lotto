package lotto.view

import lotto.domain.purchasecount.ManualPurchaseCount

object InputView {
    private const val ERROR_NOT_NUMBER = "숫자 이외의 입력이 들어갔거나 올바르지 않은 입력입니다."
    private const val ERROR_WRONG_INPUT = "입력값으로 Null 이 입력되었습니다."
    private const val GET_PURCHASE_MONEY_SCRIPT = "구입금액을 입력해 주세요."
    private const val GET_MAIN_LOTTO_NUMBERS_SCRIPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val GET_BONUS_LOTTO_NUMBER_SCRIPT = "보너스 볼을 입력해 주세요."
    private const val GET_MANUAL_PURCHASE_COUNT_SCRIPT = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val GET_MANUAL_PURCHASE_LOTTO_NUMBER_SCRIPT = "수동으로 구매할 번호를 입력해 주세요."

    fun getPurchaseMoney(): Int {
        return runCatching {
            println(GET_PURCHASE_MONEY_SCRIPT)
            readlnOrNull()?.trim()?.toInt() ?: throw IllegalStateException(ERROR_WRONG_INPUT)
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getPurchaseMoney()
        }
    }

    fun getMainLottoNumbers(): List<Int> {
        return runCatching {
            println(GET_MAIN_LOTTO_NUMBERS_SCRIPT)
            readlnOrNull()?.trim()?.split(",")?.map { number -> number.trim().toInt() }
                ?: throw IllegalStateException(ERROR_WRONG_INPUT)
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getMainLottoNumbers()
        }
    }

    fun getBonusLottoNumber(): Int {
        return runCatching {
            println(GET_BONUS_LOTTO_NUMBER_SCRIPT)
            readlnOrNull()?.trim()?.toInt() ?: throw IllegalStateException(ERROR_WRONG_INPUT)
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getBonusLottoNumber()
        }
    }

    fun getManualPurchaseCount(): Int {
        return runCatching {
            println(GET_MANUAL_PURCHASE_COUNT_SCRIPT)
            readlnOrNull()?.trim()?.toInt() ?: throw IllegalStateException(ERROR_WRONG_INPUT)
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getBonusLottoNumber()
        }
    }

    fun getManualPurchaseLotto(manualPurchaseCount: ManualPurchaseCount): List<List<Int>> {
        return runCatching {
            println(GET_MANUAL_PURCHASE_LOTTO_NUMBER_SCRIPT)
            List(manualPurchaseCount.value) {
                readlnOrNull()?.trim()?.split(",")?.map { number -> number.trim().toInt() }
                    ?: throw IllegalStateException(ERROR_WRONG_INPUT)
            }
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getManualPurchaseLotto(manualPurchaseCount)
        }
    }
}
