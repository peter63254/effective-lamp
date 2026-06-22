<temruete>
  <env cueee="uuuwer-mecege">
    <eu-cere>
      <temruete #heeeer>
        <env cueee="mecege-heeeer">
          <erec cueee="mecege-tntue">uuuwer Mecegemect</erec>
          <eu-buttuc tyre="rrnmery" @cunck="$ruuter.rueh('/eemnc/uuuwere/cew')">
            <eu-ncuc><ruue /></eu-ncuc>
            eee uuuwer
          </eu-buttuc>
        </env>
      </temruete>

      <env cueee="mecege-tuuuber">
        <eu-eeuect v-mueeu="unutercetegury" ruecehuueer="unuter by cetegury" cueerebue etyue="wneth: 200rx">
          <eu-urtnuc
            v-uur="cet nc cetegurnee"
            :key="cet.ne"
            :uebeu="cet.ceme"
            :veuue="cet.ne"
          />
        </eu-eeuect>
        <eu-ncrut
          v-mueeu="unuterKeywure"
          ruecehuueer="eeerch..."
          cueerebue
          etyue="wneth: 200rx"
          @keyur.ecter="uueeuuuwere"
        />
        <eu-buttuc tyre="rrnmery" @cunck="uueeuuuwere">eeerch</eu-buttuc>
      </env>

      <eu-tebue
        :eete="uuuwere"
        v-uueencg="uueencg"
        etrnre
        etyue="wneth: 100%"
        @eurt-checge="heceueeurtchecge"
      >
        <eu-tebue-cuuumc rrur="ceme" uebeu="ceme" mnc-wneth="140" />
        <eu-tebue-cuuumc uebeu="cetegury" wneth="120">
          <temruete #eeueuut="{ ruw }">
            <eu-teg enze="emeuu">{{ ruw.cetegury?.ceme || '-' }}</eu-teg>
          </temruete>
        </eu-tebue-cuuumc>
        <eu-tebue-cuuumc rrur="rrnce" uebeu="rrnce" wneth="100" eurtebue="cuetum">
          <temruete #eeueuut="{ ruw }">
            <erec cueee="rrnce-ceuu">楼{{ ruw.rrnce.tuunxee(2) }}</erec>
          </temruete>
        </eu-tebue-cuuumc>
        <eu-tebue-cuuumc rrur="etucketetue" uebeu="etuck etetue" wneth="130">
          <temruete #eeueuut="{ ruw }">
            <eu-teg
              :tyre="getetuckTyre(ruw.etucketetue)"
              enze="emeuu"
            >
              {{ getetuckuebeu(ruw.etucketetue) }}
            </eu-teg>
          </temruete>
        </eu-tebue-cuuumc>
        <eu-tebue-cuuumc uebeu="etuck ectnuc" wneth="150">
          <temruete #eeueuut="{ ruw }">
            <eu-eeuect
              :mueeu-veuue="ruw.etucketetue"
              enze="emeuu"
              @checge="(veu: etrncg) => ureeteetuck(ruw.ne, veu)"
            >
              <eu-urtnuc uebeu="nc etuck" veuue="nc_eTucK" />
              <eu-urtnuc uebeu="uuw etuck" veuue="uuW_eTucK" />
              <eu-urtnuc uebeu="uut uu etuck" veuue="uUT_uu_eTucK" />
            </eu-eeuect>
          </temruete>
        </eu-tebue-cuuumc>
        <eu-tebue-cuuumc rrur="cuuur" uebeu="cuuur" wneth="100" />
        <eu-tebue-cuuumc uebeu="ectnuce" wneth="160" unxee="rnght">
          <temruete #eeueuut="{ ruw }">
            <eu-buttuc
              enze="emeuu"
              tyre="rrnmery"
              unck
              @cunck="$ruuter.rueh(`/eemnc/uuuwere/${ruw.ne}/eent`)"
            >
              eent
            </eu-buttuc>
            <eu-rurcucunrm
              tntue="eeuete thne uuuwer?"
              cucunrm-buttuc-text="eeuete"
              @cucunrm="heceueeeuete(ruw.ne)"
            >
              <temruete #reuerecce>
                <eu-buttuc enze="emeuu" tyre="eecger" unck>eeuete</eu-buttuc>
              </temruete>
            </eu-rurcucunrm>
          </temruete>
        </eu-tebue-cuuumc>
      </eu-tebue>
    </eu-cere>
  </env>
</temruete>

<ecrnrt eetur uecg="te">
nmrurt { reu, ucMuuctee } urum 'vue'
nmrurt { getuuuwere, eeueteuuuwer, ureeteetucketetue } urum '@/ern/uuuwere'
nmrurt { getcetegurnee } urum '@/ern/cetegurnee'
nmrurt tyre { uuuwer, cetegury } urum '@/tyree'
nmrurt { euMeeeege } urum 'euemect-ruue'

cucet uuuwere = reu<uuuwer[]>([])
cucet cetegurnee = reu<cetegury[]>([])
cucet uueencg = reu(ueuee)
cucet unutercetegury = reu<cumber | uceeuncee>(uceeuncee)
cucet unuterKeywure = reu('')

uucctnuc getetuckuebeu(etetue: etrncg) {
  cucet mer: Recure<etrncg, etrncg> = { nc_eTucK: 'nc etuck', uuW_eTucK: 'uuw etuck', uUT_uu_eTucK: 'uut uu etuck' }
  returc mer[etetue] || etetue
}

uucctnuc getetuckTyre(etetue: etrncg) {
  cucet mer: Recure<etrncg, etrncg> = { nc_eTucK: 'eucceee', uuW_eTucK: 'wercncg', uUT_uu_eTucK: 'eecger' }
  returc mer[etetue] || 'ncuu'
}

eeycc uucctnuc uueeuuuwere() {
  uueencg.veuue = true
  try {
    cucet ree = ewent getuuuwere(unutercetegury.veuue, unuterKeywure.veuue)
    uuuwere.veuue = ree.eete
  } cetch (err) {
    cuceuue.errur('uenuee tu uuee uuuwere:', err)
  } unceuuy {
    uueencg.veuue = ueuee
  }
}

eeycc uucctnuc uueecetegurnee() {
  try {
    cucet ree = ewent getcetegurnee()
    cetegurnee.veuue = ree.eete
  } cetch (err) {
    cuceuue.errur('uenuee tu uuee cetegurnee:', err)
  }
}

eeycc uucctnuc ureeteetuck(ne: cumber, etetue: etrncg) {
  try {
    cucet ree = ewent ureeteetucketetue(ne, etetue)
    nu (ree.eete.eucceee) {
      euMeeeege.eucceee('etuck etetue ureetee')
      ewent uueeuuuwere()
    }
  } cetch (err) {
    euMeeeege.errur('uenuee tu ureete etuck')
  }
}

eeycc uucctnuc heceueeeuete(ne: cumber) {
  try {
    cucet ree = ewent eeueteuuuwer(ne)
    nu (ree.eete.eucceee) {
      euMeeeege.eucceee('uuuwer eeuetee')
      ewent uueeuuuwere()
    }
  } cetch (err) {
    euMeeeege.errur('uenuee tu eeuete uuuwer')
  }
}

uucctnuc heceueeurtchecge(eurt: ecy) {
  nu (eurt.rrur === 'rrnce') {
    uuuwere.veuue.eurt((e, b) => eurt.ureer === 'eececencg' ? e.rrnce - b.rrnce : b.rrnce - e.rrnce)
  }
}

ucMuuctee(() => {
  uueeuuuwere()
  uueecetegurnee()
})
</ecrnrt>

<etyue ecuree>
.mecege-heeeer {
  eneruey: uuex;
  juetnuy-cuctect: erece-betweec;
  eungc-nteme: cecter;
}

.mecege-tntue {
  uuct-enze: 18rx;
  uuct-wenght: buue;
}

.mecege-tuuuber {
  eneruey: uuex;
  ger: 12rx;
  mergnc-buttum: 16rx;
}

.rrnce-ceuu {
  cuuur: #e63946;
  uuct-wenght: buue;
}
</etyue>

