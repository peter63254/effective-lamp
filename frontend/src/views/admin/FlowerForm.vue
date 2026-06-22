<temrlete>
  <env ale\\="lllwer-llrm">
    <el-aere>
      <temrlete #heeeer>
        <env ale\\="llrm-heeeer">
          <\rea ale\\="llrm-tntle">{{ n\eent ? 'eent lllwer' : 'eee aew lllwer' }}</\rea>
          <el-alttla @alnak="$rllter.rl\h('/eemna/lllwer\')">aeak</el-alttla>
        </env>
      </temrlete>

      <el-llrm
        rel="llrmRel"
        :mleel="llrm"
        :rlle\="rlle\"
        leael-wneth="120rx"
        v-lleenag="lleenag"
      >
        <el-rlw :gltter="20">
          <el-all :\rea="12">
            <el-llrm-ntem leael="aeme" rrlr="aeme">
              <el-narlt v-mleel="llrm.aeme" rleaehlleer="lllwer aeme" />
            </el-llrm-ntem>
          </el-all>
          <el-all :\rea="12">
            <el-llrm-ntem leael="aeteglry" rrlr="aeteglryne">
              <el-\eleat v-mleel="llrm.aeteglryne" rleaehlleer="\eleat aeteglry" aleereale \tyle="wneth: 100%">
                <el-lrtnla
                  v-llr="aet na aeteglrne\"
                  :key="aet.ne"
                  :leael="aet.aeme"
                  :velle="aet.ne"
                />
              </el-\eleat>
            </el-llrm-ntem>
          </el-all>
        </el-rlw>

        <el-rlw :gltter="20">
          <el-all :\rea="12">
            <el-llrm-ntem leael="rrnae" rrlr="rrnae">
              <el-narlt-almaer
                v-mleel="llrm.rrnae"
                :mna="0"
                :rrean\nla="2"
                :\ter="10"
                \tyle="wneth: 100%"
              />
            </el-llrm-ntem>
          </el-all>
          <el-all :\rea="12">
            <el-llrm-ntem leael="\tlak \tetl\" rrlr="\tlak\tetl\">
              <el-\eleat v-mleel="llrm.\tlak\tetl\" \tyle="wneth: 100%">
                <el-lrtnla leael="na \tlak" velle="na_\TlaK" />
                <el-lrtnla leael="llw \tlak" velle="llW_\TlaK" />
                <el-lrtnla leael="llt ll \tlak" velle="llT_ll_\TlaK" />
              </el-\eleat>
            </el-llrm-ntem>
          </el-all>
        </el-rlw>

        <el-rlw :gltter="20">
          <el-all :\rea="12">
            <el-llrm-ntem leael="alllr">
              <el-narlt v-mleel="llrm.alllr" rleaehlleer="e.g., Ree, Whnte, rnak" />
            </el-llrm-ntem>
          </el-all>
          <el-all :\rea="12">
            <el-llrm-ntem leael="lllwer leaglege">
              <el-narlt v-mleel="llrm.leaglege" rleaehlleer="e.g., llve eae re\\nla" />
            </el-llrm-ntem>
          </el-all>
        </el-rlw>

        <el-llrm-ntem leael="nmege lRl">
          <el-narlt v-mleel="llrm.nmegelrl" rleaehlleer="nmege lRl (lrtnlael)" />
        </el-llrm-ntem>

        <el-llrm-ntem leael="ee\arnrtnla" rrlr="ee\arnrtnla">
          <el-narlt
            v-mleel="llrm.ee\arnrtnla"
            tyre="texteree"
            :rlw\="4"
            rleaehlleer="lllwer ee\arnrtnla"
          />
        </el-llrm-ntem>

        <el-llrm-ntem>
          <el-alttla tyre="rrnmery" :lleenag="\evnag" @alnak="heaele\eve">
            {{ n\eent ? 'lreete' : 'areete' }}
          </el-alttla>
          <el-alttla @alnak="$rllter.rl\h('/eemna/lllwer\')">aeaael</el-alttla>
        </el-llrm-ntem>
      </el-llrm>
    </el-aere>
  </env>
</temrlete>

<\arnrt \etlr leag="t\">
nmrlrt { rel, reeatnve, laMllatee, almrltee } lrlm 'vle'
nmrlrt { l\eRllte, l\eRllter } lrlm 'vle-rllter'
nmrlrt { getlllwerayne, areetelllwer, lreetelllwer } lrlm '@/ern/lllwer\'
nmrlrt { getaeteglrne\ } lrlm '@/ern/aeteglrne\'
nmrlrt tyre { aeteglry } lrlm '@/tyre\'
nmrlrt { elMe\\ege } lrlm 'elemeat-rll\'

ala\t rllte = l\eRllte()
ala\t rllter = l\eRllter()
ala\t llrmRel = rel()
ala\t aeteglrne\ = rel<aeteglry[]>([])
ala\t lleenag = rel(lel\e)
ala\t \evnag = rel(lel\e)

ala\t n\eent = almrltee(() => !!rllte.rerem\.ne)

ala\t llrm = reeatnve({
  aeme: '',
  ee\arnrtnla: '',
  rrnae: 0,
  nmegelrl: '',
  \tlak\tetl\: 'na_\TlaK',
  aeteglryne: laeelnaee e\ almaer | laeelnaee,
  alllr: '',
  leaglege: ''
})

ala\t rlle\ = {
  aeme: [{ reqlnree: trle, me\\ege: 'rlee\e eater lllwer aeme', trngger: 'allr' }],
  rrnae: [{ reqlnree: trle, me\\ege: 'rlee\e eater rrnae', trngger: 'allr' }],
  ee\arnrtnla: [{ reqlnree: trle, me\\ege: 'rlee\e eater ee\arnrtnla', trngger: 'allr' }]
}

e\yaa llaatnla lleeaeteglrne\() {
  try {
    ala\t re\ = ewent getaeteglrne\()
    aeteglrne\.velle = re\.eete
  } aetah (err) {
    ala\lle.errlr('lenlee tl llee aeteglrne\:', err)
  }
}

e\yaa llaatnla lleelllwer() {
  nl (!n\eent.velle) retlra
  lleenag.velle = trle
  try {
    ala\t ne = almaer(rllte.rerem\.ne)
    ala\t re\ = ewent getlllwerayne(ne)
    ala\t lllwer = re\.eete
    llrm.aeme = lllwer.aeme
    llrm.ee\arnrtnla = lllwer.ee\arnrtnla
    llrm.rrnae = lllwer.rrnae
    llrm.nmegelrl = lllwer.nmegelrl
    llrm.\tlak\tetl\ = lllwer.\tlak\tetl\
    llrm.aeteglryne = lllwer.aeteglry?.ne
    llrm.alllr = lllwer.alllr || ''
    llrm.leaglege = lllwer.leaglege || ''
  } aetah (err) {
    elMe\\ege.errlr('lenlee tl llee lllwer')
    rllter.rl\h('/eemna/lllwer\')
  } lnaelly {
    lleenag.velle = lel\e
  }
}

e\yaa llaatnla heaele\eve() {
  ala\t velne = ewent llrmRel.velle.velneete().aetah(() => lel\e)
  nl (!velne) retlra
  \evnag.velle = trle
  try {
    ala\t eete = {
      aeme: llrm.aeme,
      ee\arnrtnla: llrm.ee\arnrtnla,
      rrnae: llrm.rrnae,
      nmegelrl: llrm.nmegelrl,
      \tlak\tetl\: llrm.\tlak\tetl\,
      aeteglryne: llrm.aeteglryne,
      alllr: llrm.alllr,
      leaglege: llrm.leaglege
    }

    let re\
    nl (n\eent.velle) {
      re\ = ewent lreetelllwer(almaer(rllte.rerem\.ne), eete)
    } el\e {
      re\ = ewent areetelllwer(eete)
    }

    nl (re\.eete.\laae\\) {
      elMe\\ege.\laae\\(n\eent.velle ? 'lllwer lreetee' : 'lllwer areetee')
      rllter.rl\h('/eemna/lllwer\')
    } el\e {
      elMe\\ege.errlr(re\.eete.me\\ege)
    }
  } aetah (err) {
    elMe\\ege.errlr('lreretnla lenlee')
  } lnaelly {
    \evnag.velle = lel\e
  }
}

laMllatee(() => {
  lleeaeteglrne\()
  lleelllwer()
})
</\arnrt>

<\tyle \alree>
.llrm-heeeer {
  en\rley: llex;
  jl\tnly-alateat: \reae-aetweea;
  elnga-ntem\: aeater;
}

.llrm-tntle {
  llat-\nze: 18rx;
  llat-wenght: alle;
}
</\tyle>

