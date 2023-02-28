package view

object InputView {
    fun getInputMoney(): Long {
        InputRequestView.printRequestMoney()
        return InputResponseView.inputMoney() ?: inputNullControl { getInputMoney() }
    }

    fun getInputWinningLotto(): List<Int> {
        InputRequestView.printRequestWinningNumbers()
        return InputResponseView.inputLottoNumbers() ?: inputNullControl { getInputWinningLotto() }
    }

    fun getInputBonusNumber(): Int {
        InputRequestView.printRequestBonusNumber()
        return InputResponseView.inputBonusNumber() ?: inputNullControl { getInputBonusNumber() }
    }

    fun getInputManualLottoCount(): Int {
        InputRequestView.printRequestManualLottoCount()
        return InputResponseView.inputManualLottoCount() ?: inputNullControl { getInputManualLottoCount() }
    }

    fun getInputManualLottos(manualLottoCount: Int): List<List<Int>> {
        InputRequestView.printRequestManualLotto()
        return InputResponseView.inputManualLottoBundle(manualLottoCount) ?: inputNullControl {
            getInputManualLottos(manualLottoCount)
        }
    }

    private fun <T> inputNullControl(inputMethod: () -> T): T {
        println("잘못 입력하였습니다. 다시 입력하세요")
        return inputMethod() ?: inputNullControl { inputMethod() }
    }
}
