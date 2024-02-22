package lotto.model

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(!lotto.contains(bonusNumber)) {
            "당첨 번호와 중복되지 않는 보너스 번호를 입력해 주세요."
        }
    }
}
