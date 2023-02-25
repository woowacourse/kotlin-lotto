package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoSellerTest {
    @Test
    fun `입력받은 개수만큼 랜덤 로또를 발급한다`() {
        // given
        val randomLottoMachine = RandomLottoMachine()
        val lottoSeller = LottoSeller()

        // when
        val actual = lottoSeller.sellTicket(2, randomLottoMachine)

        // then
        assertThat(actual.size()).isEqualTo(2)
    }

    @Test
    fun `입력받은 번호로 수동 로또를 발급한다`() {
        // given
        val lottoSeller = LottoSeller()
        val lottoNumbers = listOf(
            setOf(1, 2, 3, 4, 5, 6),
            setOf(3, 4, 5, 6, 7, 8)
        )
        val manualLottoMachine = ManualLottoMachine(lottoNumbers)

        // when
        val actual = lottoSeller.sellTicket(2, manualLottoMachine)

        // then
        assertThat(actual.size()).isEqualTo(2)
    }

    @Test
    fun `입력받은 번호로 수동 로또를 발급한다2`() {
        // given
        val lottoSeller = LottoSeller()
        val lottoNumbers = listOf(
            setOf(1, 2, 3, 4, 5, 6),
            setOf(3, 4, 5, 6, 7, 8)
        )
        val manualLottoMachine = ManualLottoMachine(lottoNumbers)

        // when
        val actual = lottoSeller.sellTicket(2, manualLottoMachine)

        // then
        assertThat(actual.lottos[0].toList()).isEqualTo(listOf(1, 2, 3, 4, 5, 6).map(::LottoNumber))
    }
}
