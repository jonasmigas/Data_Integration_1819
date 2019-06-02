xquery version "1.0";


<DensidadePopulacional>
{
	for $p in doc("factos.xml")//pais
	let $d := number($p/populacao) div number($p/area)
	order by $d descending
	return <pais iso="{$p/@iso}">{$d}</pais>
}
</DensidadePopulacional>
