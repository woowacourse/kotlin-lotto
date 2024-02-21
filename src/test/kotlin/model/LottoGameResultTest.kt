package model

import org.junit.jupiter.api.Test

class LottoGameResultTest {
//    private lateinit var lottoGameResult: LottoGameResult

    @Test
    fun `일치하는 숫자의 수와 보너스 넘버 일치 여부를 통해서 올바른 랭킹 객체를 반환`() {
        // given : 준비물(객체를 만들기 위한 초기값들. 매개변수)
        val bonusNumber = LottoNumber(1)
        val winningLotto = Lotto(2, 3, 4, 5, 6, 7)
        val derivedLottie =
            listOf(
                Lotto(2, 3, 4, 5, 6, 7),
                Lotto(6, 7, 8, 9, 10, 11),
            )

        // when
        val actualResult =
            LottoGameResult(
                bonusNumber = bonusNumber,
                winningLotto = winningLotto,
                purchasedLottie = derivedLottie,
            )
    }
}

class LottoGameResult(
    bonusNumber: LottoNumber,
    winningLotto: Lotto,
    purchasedLottie: List<Lotto>,
)
