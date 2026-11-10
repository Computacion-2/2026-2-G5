
function CursosCard ({curso}) {
    const {nombre, creditos, departamento, profesor} = curso

    return (
        <div className="card">
            <h3>{nombre}</h3>
            <p className="card-subtitle">
                <strong> Creditos: </strong>{creditos} . <strong>Depto: </strong> {departamento}
            </p>
            <p className="card-profesor">
                <strong> Docente: </strong>{profesor}
            </p>
            <div style={{marginTop: '0.8rem'}}>
                <button className="btn btn-primary">Ver detalles</button>
            </div>
        </div>
    );
}

export default CursosCard