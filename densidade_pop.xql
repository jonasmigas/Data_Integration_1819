xquery version "1.0";


<DensidadePopulacional>
{
	for $p in doc("factos.xml")//pais
	let $d := number($p/area) div number($p/populacao)
	order by $d
	return <pais iso="{$p/@iso}">{$d}</pais>
}
</DensidadePopulacional>
