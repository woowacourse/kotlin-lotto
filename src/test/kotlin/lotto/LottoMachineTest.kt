package lotto

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `로또 번호를 넘겨주면, 입력한 로또 번호를 가진 로또를 생성한다`() {
        val lottoNumbers: List<LottoNumber> =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val actual: Lotto = lottoMachine.createLotto(lottoNumbers)

        val expected = Lotto(1, 2, 3, 4, 5, 6)

        assertThat(actual).isEqualTo(expected)
    }
}
