const cargo = process.argv[2];
if (!cargo) {
    console.error("Usage: provide cargo string, e.g. AABABBAB");
    process.exit(1);
}
console.log(`Cargo: ${cargo}`);
// TODO: implement solution
