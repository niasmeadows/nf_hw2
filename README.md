# nf_hw2
nextflow homework 2
# Trim and Assemble Paired-End FastQ 

## Objective of this workflow (besides just for a homework assignment)
This Nextflow workflow is designed to take paired-end reads, assemble them, and quality assess/genotype in parallel. The script takes paired-end reads in fastq.gz format as input and performs assembly with SPAdes to generate fasta format output files then does MLST and QUAST.

## WHAT YOU NEED ON YOUR MACHINE/ENVIRONMENT (and all their dependencies)
1. Nextflow
2. Spades
3. MLST
4. QUAST

## THE FILES GOING IN
Paired-end reads in fastq.gz format. Naming conventions of these files should be:
1.`{sample}_1.fastq.gz`
2.`{sample}_2.fastq.gz`

## WHAT TO SPECIFY
1. `params.reads`: the input paired-end reads in fastq.gz format.
2. `params.outdir`: the output directory where the assembled fasta files will be put

## THE FLOW
To execute the workflow, follow these steps:
1. Install the appropriate packages.
2. Execute the workflow using the following command:
   ```
   nextflow run workflow_HW2.nf
   ```

## WHATS COMING OUT
The workflow will produce trimmed and assembled fasta files for each pair of reads. These files can be found in the specified output directory.
