package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.ManualLottoNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoNumbersTest {
    @Test
    fun `수동 로또 번호 리스트를 Lotto 리스트로 변환할 수 있다`() {
        val manualNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )
        val manualLottoNumbers = ManualLottoNumbers(manualNumbers)

        val lottos = manualLottoNumbers.toLottos()

        assertThat(lottos).hasSize(2)
        assertThat(lottos[0]).isEqualTo(Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }))
        assertThat(lottos[1]).isEqualTo(Lotto(listOf(7, 8, 9, 10, 11, 12).map { LottoNumber.of(it) }))
    }
}
