package lotto

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbersGenerator
import lotto.model.ManualLottoLottoNumbersMachine
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `수동 로또 번호 생성기와 발행할 로또 개수를 넣어주면, 그 개수만큼 로또를 발행한다`() {
        // given
        val numbersBundle: List<List<LottoNumber>> =
            listOf(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val manualNumbersGenerator = ManualLottoLottoNumbersMachine(numbersBundle)
        val lottoCount = LottoCount(1)

        // when
        val lottos: List<Lotto> = lottoMachine.createLottos(lottoCount, manualNumbersGenerator)

        // then
        assertAll(
            { Assertions.assertThat(lottos.size).isEqualTo(1) },
            { Assertions.assertThat(lottos[0]).isEqualTo(Lotto.from(numbersBundle[0])) },
        )
    }

    @Test
    fun `자동 로또 번호 생성기와 발행할 로또 개수를 넣어주면, 그 개수만큼 로또를 발행한다`() {
        // given
        val autoLottoNumbersGenerator = TestLottoNumbersGenerator()
        val lottoCount = LottoCount(1)

        // when
        val lottos: List<Lotto> = lottoMachine.createLottos(lottoCount, autoLottoNumbersGenerator)

        // then
        assertAll(
            { Assertions.assertThat(lottos.size).isEqualTo(1) },
            { Assertions.assertThat(lottos[0]).isEqualTo(Lotto.from(1, 2, 3, 4, 5, 6)) },
        )
    }

    class TestLottoNumbersGenerator : LottoNumbersGenerator {
        override fun generate(): List<LottoNumber> =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
    }
}
