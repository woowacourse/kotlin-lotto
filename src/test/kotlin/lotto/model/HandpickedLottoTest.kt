package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandpickedLottoTest {
    @Test
    fun `수동 로또 발행 확인`() {
        val handpickedNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )

        val handpickedLotto = HandpickedLotto(handpickedNumbers).generateLotto()

        assertThat(handpickedLotto.getNumbers().containsAll(handpickedNumbers))
    }
}
