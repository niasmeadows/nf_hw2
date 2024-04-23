#!/usr/bin/ nextflow
nextflow.enable.dsl=2

params.reads = "*_{1,2}.fastq.gz"
params.outdir = '.'

// Define channels
//reads_ch=Channel.fromPath(params.reads)

// Assembly with SPAdes
process assembly {
    tag "${id}"

    input:
    tuple val(id), path(read1), path(read2)

    output:
    tuple val(id), path("${id}.fasta"), emit: assembly_out

    //for spades, the usage is spades.py -1 <first read > -2 <second read > -o <output directory>
    script:
    """
    spades.py -1 ${id}_1.fastq.gz -2 ${id}_2.fastq.gz -o ${id}.fasta
    """
}

//Quality Assessment using QUAST
process quality_assessment {
    input:
    tuple val(id), path(assembly_out)
    
    output:
    path("${id}_quast_report")

    script:
    """
    mkdir ${id}_quast_report
    quast.py "${id}.fasta/contigs.fasta" --min-contig 100 -o ${id}_quast_report
    """
}
// // Genotyping with MLST
// process genotyping {
//     input: 
//     tuple val(id), path(assembly_out)

//     output:
//     path("${assembly_dir}_MLST_Summary.tsv")
//     script:
//     """
//     mlst "${id}/contigs.fasta" > ${id}_MLST_Summary.tsv
//     """
// }

//reads = Channel.fromPath(params.reads, checkIfExists: true)

read_pairs = Channel.fromFilePairs(params.reads, size: 2, flat: true)

workflow {
    assem = assembly(read_pairs)
    quality_assessment(assem)
    //genotyping(assem)
}
